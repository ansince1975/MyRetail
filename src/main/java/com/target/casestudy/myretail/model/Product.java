package com.target.casestudy.myretail.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product
{
    private long id;

    public long getId() {
        return id;
    }

    public Price getCurrent_price() {
        return current_price;
    }

    private String name;

    private Price current_price;

    public Product(long id, String name, Price price) {
        this.id = id;
        this.name = name;

        this.current_price = price;
    }
}