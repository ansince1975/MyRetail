package com.target.casestudy.myretail.exception;

public class ProductServiceException extends Exception {

  private static final long serialVersionUID = 1L;

  private String code;
  private String message;

  public ProductServiceException(String code) {
    super();

    this.code = code;
  }

  //Anything extra parameters can be sent through msgParameters
  public ProductServiceException(String code, String message, Object... msgParameters) {
    super();

    this.code = code;
    this.message = msgParameters == null ? message : String.format(message, msgParameters);
  }

  public String getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "ProductServiceException{"
        + "code='" + code + '\''
        + ", message='"
        + message + '\''
        + '}';
  }
}
