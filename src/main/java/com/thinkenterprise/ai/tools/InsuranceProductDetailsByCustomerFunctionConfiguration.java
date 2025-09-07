package com.thinkenterprise.ai.tools;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Profile;

import com.thinkenterprise.ai.parameter.ProductRequest;
import com.thinkenterprise.domain.Product;
import com.thinkenterprise.service.InsuranceProductService;

@Configuration
@Profile("!mcp")
public class InsuranceProductDetailsByCustomerFunctionConfiguration {

    @Bean("get_ProductDetailsByCustomer")
    @Description("Stellt eine Anfrage an den Versicherungsservice, um Produktdaten zu erhalten.")
    public Function<ProductRequest,Product> createProductDetailsByCustomerFunction(InsuranceProductService insuranceProductService) {
     return (customer) -> insuranceProductService.getProductDetailsByCustomer(customer.getName());

    }
    
}
