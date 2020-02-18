package com.target.casestudy.myretail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.casestudy.myretail.config.ApplicationConfig;
import com.target.casestudy.myretail.config.CassandraConfig;
import com.target.casestudy.myretail.exception.ProductNotFoundException;
import com.target.casestudy.myretail.exception.ProductServiceException;
import com.target.casestudy.myretail.model.Price;
import com.target.casestudy.myretail.model.Product;
import com.target.casestudy.myretail.model.RootObject;
import com.target.casestudy.myretail.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.invoke.MethodHandles;

@Service
public class ProductsService {


    protected RestTemplate restTemplate;
    protected ObjectMapper objectMapper;
    protected PriceService priceService;

    @Autowired
    ApplicationConfig applicationConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup()
            .lookupClass());

    public ProductsService(RestTemplate restTemplate, ObjectMapper mapper,
                           PriceService priceService, ApplicationConfig applicationConfig)
    {
        this.restTemplate = restTemplate;
        this.objectMapper =  mapper;
        this.priceService = priceService;
        this.applicationConfig = applicationConfig;
    }


    public Product getProduct(long productId) throws ProductServiceException, ProductNotFoundException{
        Price price = null;
        Product productResponse = null;
        String productTitle;

        productTitle = getTitle(productId).toString();

        price  = priceService.fetchPrice(String.valueOf(productId));

        productResponse = new Product(productId, productTitle, price);
        return productResponse;
    }


    private JsonNode getTitle(long productId) throws ProductServiceException, ProductNotFoundException{

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = applicationConfig.getRedskyUrl();
        url = url.replace("<InputProductId>", String.valueOf(productId));

        LOGGER.info("loadConfigurations: url={}", url);

        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity(headers),
                    String.class
            );

        }

        catch (HttpClientErrorException e ) {
            throw new ProductNotFoundException(e.getLocalizedMessage());
        }

        catch (Exception e) {
            throw new ProductServiceException("PRD002", e.getLocalizedMessage());
        }

        String jsonResponse = response.getBody();

        if (jsonResponse == null) {
            LOGGER.error("No ProductPriceEntity/insufficient quantity found for the ProductPriceEntity " + productId);
            throw new ProductNotFoundException("No ProductPriceEntity found for the id " + productId);
        }

        LOGGER.debug("Response from redksy target link ", jsonResponse);

        RootObject root = null;
        JsonNode productTitle = null;
        try {
            JsonNode rootProductObject = objectMapper.readTree(jsonResponse);
            productTitle = rootProductObject.at("/product/item/product_description/title");
        }
        catch (JsonProcessingException e) {
            //@TODO Error code can be configured to fetch from Properties file or Persistant Key Value Storage
            LOGGER.error(e.getLocalizedMessage());
            throw new ProductServiceException("PRD001", "Error in Processing the Response from redsky target product link");
        }

        catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new ProductServiceException("PRD002", e.getLocalizedMessage());
        }

        return productTitle;
    }



}
