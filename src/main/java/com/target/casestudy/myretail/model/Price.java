package com.target.casestudy.myretail.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Price {
    private double value;

    public double getValue() {
        return value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    private String currencyCode;

    public Price(double value, String currencyCode) {
        this.currencyCode = currencyCode;
        this.value = value;

    }

}
