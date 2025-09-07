package com.thinkenterprise.ai.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.thinkenterprise.ai.properties.InsuranceProperties;
import com.thinkenterprise.domain.Customer;
import com.thinkenterprise.service.InsuranceCustomerService;

@Component
@Profile("!mcp")
public class InsuranceCustomerDetailsTool {

    static final Logger logger = LoggerFactory.getLogger(InsuranceCustomerDetailsTool.class);

    private InsuranceCustomerService insuranceCustomerService;
    private InsuranceProperties insuranceProperties;

    public InsuranceCustomerDetailsTool(InsuranceCustomerService insuranceCustomerService, InsuranceProperties insuranceProperties) {
       this.insuranceCustomerService=insuranceCustomerService;
       this.insuranceProperties=insuranceProperties;
    }

    @Tool(name = "get_CustomerDetails", description = "Stellt eine Anfrage an den Versicherungsservice, um Kundendaten zu erhalten.")
    public Customer getCustomerDetails(@ToolParam(required = true, description = "Name des Kunden") String name, ToolContext context) {
        logger.info(context.getContext().get("databaseHost").toString());
        if(insuranceProperties.exception())
        throw new InsuranceException("Konnte Customer Details nicht ermitteln");
        return insuranceCustomerService.getCustomerDetails(name);
    }

}
