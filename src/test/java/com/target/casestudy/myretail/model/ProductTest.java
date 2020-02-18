package com.target.casestudy.myretail.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductTest {
    private Product product;

    @Before
    public void setUp() {
        Price price = new Price(12345.67, "USD");
        product = new Product(1, "Science of Self Realization", price);
    }

    @Test
    public void getProductId() {
        double expected = 1;
        double actual = product.getId();

        assertEquals(expected, actual);
    }

    @Test
    public void getCurrency() {
        String expected = "USD";
        String actual = product.getCurrent_price().getCurrencyCode();

        assertEquals(expected, actual);
    }

}



