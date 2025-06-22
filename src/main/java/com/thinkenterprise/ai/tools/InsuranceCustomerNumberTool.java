package com.thinkenterprise.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import com.thinkenterprise.domain.Customer;
import com.thinkenterprise.service.InsuranceCustomerService;

@Service
public class InsuranceCustomerNumberTool {

    private InsuranceCustomerService insuranceCustomerService;

    public InsuranceCustomerNumberTool(InsuranceCustomerService insuranceCustomerService) {
       this.insuranceCustomerService=insuranceCustomerService;
    }

    @Tool(name = "get_CustomerDetails", description = "Stellt eine Anfrage an den Versicherungsservice, um Kundendaten zu erhalten.")
    public Customer getCustomerDetails(@ToolParam(required = true, description = "Name des Kunden") String name) {
        return insuranceCustomerService.getCustomerData(name);
    }

}
