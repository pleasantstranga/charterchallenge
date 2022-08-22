package com.charter.service;

import com.charter.exception.RecordNotFoundException;
import com.charter.entities.Customer;
import com.charter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer findByCustomerId(Integer id) throws RecordNotFoundException {
        Optional<Customer> cust =  customerRepository.findById(id);
        if(cust.isPresent()) {
            return cust.get();
        }
        else {
            throw new RecordNotFoundException("Customer Not Found");
        }
    }

    @Transactional
    public Set<Customer> getLast3Months()  {

        LocalDate threeMonthDate = LocalDate.now().minusMonths(3L);

        Date date = Date.from(threeMonthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return customerRepository.findAllCustomers(date);
    }

}