package com.charter.service;

import com.charter.entities.*;
import com.charter.models.MonthlyPoints;
import com.charter.models.TalliedMonthlyPoints;
import com.charter.utils.DateUtil;
import org.springframework.stereotype.Service;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
@Service
public class TransactionService {

    public Map<String, List<MonthlyPoints>> calculateResults(Set<Customer> customers) {
        Map<String, List<MonthlyPoints>> custMonthlyPoints = new HashMap<>();
        //iterate over all customer's transactions
        customers.stream().forEach((customerObj) -> {
            //iterate over all customer's transactions
            customerObj.getTransactions().stream().forEach(transaction -> {
                Long points = generatePoints(transaction);
                Month month = DateUtil.convertDateToLocalDate(transaction.getDate()).getMonth();
                addMonthlyTransactions(custMonthlyPoints, customerObj, points, month);
            });

        });
        return custMonthlyPoints;
    }

    private void addMonthlyTransactions(Map<String, List<MonthlyPoints>> custMonthlyPoints, Customer customerObj, Long points, Month month) {
        if(custMonthlyPoints.containsKey(customerObj.getName())) {
            addTransactionPoints(month, points, custMonthlyPoints.get(customerObj.getName()));
        }
        else {
            List<MonthlyPoints> mps = new ArrayList<>();
            mps.add(new MonthlyPoints(month, points));
            custMonthlyPoints.put(customerObj.getName(), mps);
        }
    }

    private static Long generatePoints(Transactions transaction) {
        Long points = 0L;
        Integer totalAbove100 = transaction.getAmount() - 100;

        if (totalAbove100 > 0) {
            points += (totalAbove100 * 2);
        }
        if (transaction.getAmount() > 50) {
            points += 50;
        }
        return points;
    }

    private void addTransactionPoints(Month month, Long pointsToAdd,List<MonthlyPoints> monthlyPoints) {
        if(monthlyPoints == null) {
            monthlyPoints = new ArrayList<>();
        }
        AtomicBoolean foundMonth = new AtomicBoolean(false);
        monthlyPoints.stream().forEach(mp -> {
            if(mp.getMonth().equals(month)) {
                if(null == mp.getPoints()) {
                    mp.setPoints(Arrays.asList(pointsToAdd));
                }
                else {
                    mp.getPoints().add(pointsToAdd);
                }
                foundMonth.set(true);
            }
        });
        if(!foundMonth.get()) {
            monthlyPoints.add(new MonthlyPoints(month, pointsToAdd));
        }
    }

    public Map<String, List<TalliedMonthlyPoints>> tallyPointsByMonth(Map<String, List<MonthlyPoints>> monthlyPoints) {
        Map<String, List<TalliedMonthlyPoints>> tallies = new HashMap<>();
        monthlyPoints.entrySet().stream().forEach(mp -> {
            List<TalliedMonthlyPoints> talliedPoints = new ArrayList<>();
            mp.getValue().stream().forEach(monthlyValues -> {
                List<Long> points = monthlyValues.getPoints();
                talliedPoints.add(new TalliedMonthlyPoints(monthlyValues.getMonth(), points));
            });
            tallies.put(mp.getKey(), talliedPoints);
        });
        return tallies;

    }
}

