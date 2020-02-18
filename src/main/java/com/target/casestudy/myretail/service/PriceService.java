package com.target.casestudy.myretail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.casestudy.myretail.config.CassandraConfig;
import com.target.casestudy.myretail.entities.ProductPriceEntity;
import com.target.casestudy.myretail.exception.ProductServiceException;
import com.target.casestudy.myretail.model.Price;
import com.target.casestudy.myretail.model.Product;
import com.target.casestudy.myretail.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

@Service
public class PriceService {

    protected ObjectMapper objectMapper;

    private ProductRepository productRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup()
            .lookupClass());

    @Autowired
    public PriceService(ObjectMapper mapper, ProductRepository productRepository)
    {
        this.objectMapper = mapper;
        this.productRepository = productRepository;
    }

    public Price fetchPrice(String productId) throws ProductServiceException {

        LOGGER.info("Fetching Price for " + productId);
        ProductPriceEntity entity =  productRepository.findById(productId).get();
        return (entity != null? new Price(Double.valueOf(entity.getPrice()), entity.getCurrency_Code()): null);
    }

    public Product updatePrice(long id, String productString) throws ProductServiceException {

        Product product = null;
        boolean success = false;

        try {
            JsonNode rootProductObject = objectMapper.readTree(productString);
            String productId = rootProductObject.at("/id").toString();
            String value = rootProductObject.at("/current_price/value").toString();
            String currCode = rootProductObject.at("/current_price/currencycode").toString();

            ProductPriceEntity entityIn = new ProductPriceEntity();
            entityIn.setCurrency_Code(currCode);
            entityIn.setPrice(String.valueOf(value));
            entityIn.setProductId(String.valueOf(productId));
            ProductPriceEntity entityOut =  productRepository.save(entityIn);
            LOGGER.info("Successfully saved price for " + productId);
            success = true;
        }

        catch (JsonProcessingException e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new ProductServiceException("PRD001", "Error in parsing the input Json for Price Update");

        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new ProductServiceException("PRD002", "Error in updating the Price into Persistant Storage");
        }

        return (success ? product : null);
    }
}
