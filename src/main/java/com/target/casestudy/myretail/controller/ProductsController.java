package com.target.casestudy.myretail.controller;

import com.target.casestudy.myretail.config.ApplicationConfig;
import com.target.casestudy.myretail.config.CassandraConfig;
import com.target.casestudy.myretail.exception.ProductNotFoundException;
import com.target.casestudy.myretail.exception.ProductServiceException;
import com.target.casestudy.myretail.model.Product;
import com.target.casestudy.myretail.service.PriceService;
import com.target.casestudy.myretail.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductsController {


    @Autowired
    ProductsService productsService;

    @Autowired
    PriceService priceService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup()
            .lookupClass());

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductDetails(@PathVariable long id)
            throws ProductNotFoundException, ProductServiceException {
        LOGGER.info("Getting ProductPriceEntity Details for product code " + id);


        /*
        TODO I have made calls sequentially first to get title and to nosql db to get price details.
        This can be enhanced use CompletableFuture to make async calls to url service and price service
        collate the response back and sent it back to the caller in a sync way.
         */

        Product product = productsService.getProduct(id);

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }


    @PutMapping(value = "/updateproduct/{id}/price",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Product> updatePrice(@PathVariable long id,
                                              @RequestBody String payload)
            throws ProductNotFoundException, ProductServiceException {
        LOGGER.info("Updating Price Details");

        Product product = priceService.updatePrice(id, payload);

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

}
