package pl.akademia.api.exceptions;

public class BasketNotFoundException extends RuntimeException{
    public BasketNotFoundException(String message) {
        super(message);
    }
}
