package pl.akademia.api.exceptions;

public class RequestBodyHasNullFieldException extends RuntimeException {

  public RequestBodyHasNullFieldException(String message) {
    super(message);
  }
}
