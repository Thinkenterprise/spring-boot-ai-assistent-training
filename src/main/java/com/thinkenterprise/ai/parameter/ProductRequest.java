package com.thinkenterprise.ai.parameter;

import org.springframework.ai.tool.annotation.ToolParam;

public class ProductRequest {

    @ToolParam(description = "Name des Kunden", required = true)
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
