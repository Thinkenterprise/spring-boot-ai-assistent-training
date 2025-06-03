package com.thinkenterprise.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import com.thinkenterprise.domain.Customer;
import com.thinkenterprise.service.InsuranceCustomerService;

@Service
public class InsuranceCustomerNumberTool {

    private InsuranceCustomerService insuranceCustomerService;

    public InsuranceCustomerNumberTool(InsuranceCustomerService insuranceCustomerService) {
       this.insuranceCustomerService=insuranceCustomerService;
    }

    @Tool(description = "Stellt eine Anfrage an den Versicherungsservice, um Kundendaten zu erhalten.")
    public Customer getCustomerData(String name) {
        return insuranceCustomerService.getCustomerData(name);
    }

}
