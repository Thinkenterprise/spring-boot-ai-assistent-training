package com.thinkenterprise.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.thinkenterprise.domain.Product;

@Service
public class InsuranceProductService {

    public Product getProductDetailsByCustomer(String customer) {
       return new Product("PL2345", "Live 100", LocalDate.of(1985, 4, 14));
    }
    
}
