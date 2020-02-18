package com.target.casestudy.myretail.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    public ErrorResponse(String errorDescription, List<String> errorDetails) {
        this.errorDescription =  errorDescription;
        this.errorDetails = errorDetails;
    }

    String errorDescription;
    List<String> errorDetails = new ArrayList<>();
}
