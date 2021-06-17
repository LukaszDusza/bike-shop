package pl.akademia.api.exceptions;

public class OrderNotFoundByException extends RuntimeException{

    public OrderNotFoundByException(String message) {
        super(message);
    }
}
