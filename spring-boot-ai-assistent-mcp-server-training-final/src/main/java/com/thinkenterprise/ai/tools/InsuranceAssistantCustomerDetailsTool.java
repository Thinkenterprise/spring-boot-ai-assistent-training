package com.thinkenterprise.ai.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import com.thinkenterprise.domain.Customer;
import com.thinkenterprise.service.InsuranceAssistentCustomerService;

@Component
public class InsuranceAssistantCustomerDetailsTool {

    static final Logger logger = LoggerFactory.getLogger(InsuranceAssistantCustomerDetailsTool.class);

    private InsuranceAssistentCustomerService insuranceCustomerService;
   
   
    public InsuranceAssistantCustomerDetailsTool(InsuranceAssistentCustomerService insuranceCustomerService) {
       this.insuranceCustomerService=insuranceCustomerService;
    }

    @McpTool(name = "getCustomerDetails", description = "Ermittelt Kundendaten eines Kunden")
    public Customer getCustomerDetails(@ToolParam(required = true, description = "Name des Kunden") String customerName) {

        return insuranceCustomerService.getCustomerDetails(customerName);
    }

}
