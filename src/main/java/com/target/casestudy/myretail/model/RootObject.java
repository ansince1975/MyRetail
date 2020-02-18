package com.target.casestudy.myretail.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "product"
})
public class RootObject {

    @JsonProperty("product")
    private Product product;

    @JsonProperty("product")
    public Product getProduct() {
        return product;
    }

}
