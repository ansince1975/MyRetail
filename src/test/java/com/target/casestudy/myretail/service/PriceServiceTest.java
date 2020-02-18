package com.target.casestudy.myretail.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.casestudy.myretail.exception.ProductServiceException;
import com.target.casestudy.myretail.model.Price;
import com.target.casestudy.myretail.model.Product;
import com.target.casestudy.myretail.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class PriceServiceTest {

    @Mock
    protected ObjectMapper objectMapper;

    @Mock
    ProductRepository productRepository;
    @Spy
    @InjectMocks
    private PriceService priceService = new PriceService(objectMapper, productRepository);

    @Test
    public void fetchPriceTest() throws ProductServiceException {
        long productId = 12345L;

        Price priceActual = null;
        Price priceExpected = null;

        priceActual = priceService.fetchPrice(String.valueOf(productId));

        Assert.assertNotNull(priceActual);

        Mockito.when(priceService.fetchPrice(Mockito.anyString()))
                .thenReturn(priceExpected);

        Assert.assertEquals(priceExpected, priceActual);
    }


    @Test
    public void updatePriceTest() throws ProductServiceException {
        long productId = 12345L;
        String productString = "JSON STRING";


        Product productActual = null;
        Product productExpected = null;

        productActual = priceService.updatePrice(productId, productString);
        Assert.assertNotNull(productActual);

        Mockito.when(priceService.updatePrice(Mockito.anyLong(), Mockito.anyString()))
                .thenReturn(productExpected);

        Assert.assertEquals(productExpected, productActual);

    }
}