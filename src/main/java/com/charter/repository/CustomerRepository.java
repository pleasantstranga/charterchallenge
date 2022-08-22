package com.charter.repository;

import com.charter.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "SELECT cust FROM Customer cust LEFT JOIN FETCH cust.transactions")
    Set<Customer> findAllCustomers();

    @Query(value = "SELECT cust FROM Customer cust LEFT JOIN FETCH cust.transactions t where t.date >= :date")
    Set<Customer> findAllCustomers(@Param("date") Date date);
}