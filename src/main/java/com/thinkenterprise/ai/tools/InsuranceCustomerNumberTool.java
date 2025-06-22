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

<<<<<<< HEAD
    @Tool(name = "get_CustomerDetails", description = "Stellt eine Anfrage an den Versicherungsservice, um Kundendaten zu erhalten.")
    public Customer getCustomerDetails(@ToolParam(required = true, description = "Name des Kunden") String name) {
=======
    @Tool(name = "ToolName", description = "Stellt eine Anfrage an den Versicherungsservice, um Kundendaten zu erhalten.")
    public Customer getCustomerData(String name) {
>>>>>>> 95bb83c7f58a929220b0d9b9ecf6f4785b4317c2
        return insuranceCustomerService.getCustomerData(name);
    }

}
