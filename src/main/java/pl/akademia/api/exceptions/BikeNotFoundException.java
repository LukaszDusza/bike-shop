package pl.akademia.api.exceptions;

public class BikeNotFoundException extends RuntimeException {

  public BikeNotFoundException(String message) {

    super(message);
  }
}
