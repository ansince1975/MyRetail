package com.target.casestudy.myretail.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PriceTest {
    private Price price;

    @Before
    public void setUp() {
        price = new Price(12345.67, "USD");
    }

    @Test
    public void getValue() {
        double expected = 12345.67;
        double actual = price.getValue();

        assertEquals(expected, actual);
    }

    @Test
    public void getCurrency() {
        String expected = "USD";
        String actual = price.getCurrencyCode();

        assertEquals(expected, actual);
    }

}
