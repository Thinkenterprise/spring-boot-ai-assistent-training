package com.thinkenterprise.ai.tools;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import com.thinkenterprise.ai.parameter.ProductRequest;
import com.thinkenterprise.domain.Product;
import com.thinkenterprise.service.InsuranceProductService;

@Configuration
public class InsuranceProductDetailsByCustomerFunctionConfiguration {

    @Bean("get_ProductDetailsByCustomer")
    @Description("Stellt eine Anfrage an den Versicherungsservice, um Produktdaten zu erhalten.")
    public Function<ProductRequest,Product> createProductDetailsByCustomerFunction(InsuranceProductService insuranceProductService) {
     return (customer) -> insuranceProductService.getProductDetailsByCustomer(customer.getName());

    }
    
}
