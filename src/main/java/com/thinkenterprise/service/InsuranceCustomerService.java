package com.thinkenterprise.service;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

import com.thinkenterprise.domain.Customer;

@Service
public class InsuranceCustomerService {

    public Customer getCustomerData(String Name) {
       return new Customer("IN2000", "Michael", LocalDate.of(1970, 4, 14));
    }
}
