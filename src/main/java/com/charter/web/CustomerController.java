package com.charter.web;

import java.util.List;
import java.util.Map;

import com.charter.entities.Customer;
import com.charter.models.MonthlyPoints;
import com.charter.models.TalliedMonthlyPoints;
import com.charter.service.CustomerService;
import com.charter.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charter.exception.RecordNotFoundException;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    private CustomerService service;
    private TransactionService tService;

    @Autowired
    public CustomerController(CustomerService service, TransactionService tService) {
        this.service = service;
        this.tService = tService;
    }

    @GetMapping
    public ResponseEntity<String> getLast3MonthsForAllEmployees() throws JsonProcessingException {
        Map<String, List<MonthlyPoints>> monthlyPoints =  tService.calculateResults(service.getLast3Months());
        Map<String, List<TalliedMonthlyPoints>> tallied = tService.tallyPointsByMonth(monthlyPoints);

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return new ResponseEntity<>(writer.writeValueAsString(tallied), new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getEmployeeById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
        Customer entity = service.findByCustomerId(id);

        return new ResponseEntity<Customer>(entity, new HttpHeaders(), HttpStatus.OK);
    }

}