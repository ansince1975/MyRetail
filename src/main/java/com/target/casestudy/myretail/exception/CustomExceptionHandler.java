package com.target.casestudy.myretail.exception;

import com.target.casestudy.myretail.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup()
            .lookupClass());

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<Object> handle(ProductNotFoundException exception) {
        List<String> errorDetails = new ArrayList<>();
        errorDetails.add(exception.getMessage());
        ErrorResponse error = new ErrorResponse("ProductPriceEntity Not Found", errorDetails);
        //Sends Status code 404

        LOGGER.info("Catching ProductNotFoundException and preparing to handle it");
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }


}