package com.thinkenterprise.ai.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import com.thinkenterprise.domain.Customer;
import com.thinkenterprise.service.InsuranceCustomerService;

@Component
public class InsuranceCustomerDetailsTool {

    static final Logger logger = LoggerFactory.getLogger(InsuranceCustomerDetailsTool.class);

    private InsuranceCustomerService insuranceCustomerService;
   
    public InsuranceCustomerDetailsTool(InsuranceCustomerService insuranceCustomerService) {
       this.insuranceCustomerService=insuranceCustomerService;
    }

    @Tool(name = "getCustomerDetails", description = "Ermittelt Kundendaten eines Kunden")
    public Customer getCustomerDetails(@ToolParam(required = true, description = "Name des Kunden") String name) {
        return insuranceCustomerService.getCustomerDetails(name);
    }

}
