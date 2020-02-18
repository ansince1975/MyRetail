package com.target.casestudy.myretail.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ProductControllerTest {

    @InjectMocks
    private ProductsController productsController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productsController).build();
    }

    @Test
    public void getProductTest() throws Exception {
        this.mockMvc.perform(get("/products/12345")).andExpect(status().isOk());
    }

    @Test
    public void updateProductTest() throws Exception {
        this.mockMvc.perform(get("/products/updateproduct/12345/price")).andExpect(status().isOk());
    }

}