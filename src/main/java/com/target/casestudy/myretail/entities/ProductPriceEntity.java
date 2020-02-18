package com.target.casestudy.myretail.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Data
@NoArgsConstructor
public class ProductPriceEntity {

    @Id
    @PrimaryKey
    private String productId;
    private String price;
    private String currency_code;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency_Code() {
        return currency_code;
    }

    public void setCurrency_Code(String currencyCode) {
        this.currency_code = currencyCode;
    }
}
