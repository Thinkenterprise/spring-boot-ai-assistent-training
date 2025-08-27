package com.thinkenterprise.ai.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "thinkenterprise")
public record InsuranceProperties(Boolean exception) {
}
