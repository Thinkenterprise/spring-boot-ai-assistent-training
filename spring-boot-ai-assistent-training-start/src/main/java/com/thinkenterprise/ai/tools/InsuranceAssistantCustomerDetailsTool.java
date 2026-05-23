package com.thinkenterprise.ai.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import com.thinkenterprise.domain.Customer;
import com.thinkenterprise.service.InsuranceAssistentCustomerService;

@Component
public class InsuranceAssistantCustomerDetailsTool {

    static final Logger logger = LoggerFactory.getLogger(InsuranceAssistantCustomerDetailsTool.class);

    private InsuranceAssistentCustomerService insuranceCustomerService;
    private InsuranceProperties insuranceProperties;
   
    public InsuranceAssistantCustomerDetailsTool(InsuranceAssistentCustomerService insuranceCustomerService,
                                                 InsuranceProperties insuranceProperties
    ) {
       this.insuranceCustomerService=insuranceCustomerService;
       this.insuranceProperties=insuranceProperties;
    }

    @Tool(name = "getCustomerDetails", description = "Ermittelt Kundendaten eines Kunden")
    public Customer getCustomerDetails(@ToolParam(required = true, description = "Name des Kunden") String name, 
                                        ToolContext context) {
        logger.info(context.getContext().get("session").toString());
        if(insuranceProperties.exception())
        throw new InsuranceException("Konnte Customer Details nicht ermitteln");
        return insuranceCustomerService.getCustomerDetails(name);
    }

}
