package pl.akademia.api.exceptions;

public class FileTypeNotFoundException extends RuntimeException {

    public FileTypeNotFoundException(String message) {
        super(message);
    }
}
