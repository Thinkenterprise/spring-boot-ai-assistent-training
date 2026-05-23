package com.thinkenterprise.ai.tools;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "thinkenterprise")
public record InsuranceProperties(Boolean exception) {

    

}
