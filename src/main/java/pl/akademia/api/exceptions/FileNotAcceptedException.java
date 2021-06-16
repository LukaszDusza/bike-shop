package pl.akademia.api.exceptions;

public class FileNotAcceptedException extends RuntimeException {

    public FileNotAcceptedException(String message) {
        super(message);
    }
}
