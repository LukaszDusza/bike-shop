package pl.akademia.api.exceptions;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {BikeNotFoundException.class})
  protected ResponseEntity<Object> handleNotFoundException(RuntimeException e, WebRequest request) {
    ExceptionBody body = ExceptionBody
        .builder()
        .message(e.getMessage() + " serialNumber: " + request.getParameter("serial"))
        .status(404)
        .path(request.getDescription(true))
        .timestamp(new Date().toString())
        .build();
    return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {OrderNotFoundException.class})
  protected ResponseEntity<Object> handleOrderNotFoundException(RuntimeException e, WebRequest request) {
    String min = request.getParameter("min");
    String max = request.getParameter("max");
    ExceptionBody body = ExceptionBody
            .builder()
            .message(e.getMessage() + " range: " + min + " - " + max)
            .status(404)
            .path(request.getDescription(true))
            .timestamp(new Date().toString())
            .build();
    return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {ClientNotFoundException.class})
  protected ResponseEntity<Object> handleNotFoundClientException(RuntimeException e, WebRequest request) {
    String param = request.getParameter("id");
    ExceptionBody body = ExceptionBody
        .builder()
        .message(e.getMessage() + " id: " + param)
        .status(HttpStatus.NOT_FOUND.value())
        .path(request.getDescription(true))
        .timestamp(new Date().toString())
        .build();
    return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {PropertyValueException.class})
  protected ResponseEntity<Object> handleNullableValueException(PropertyValueException e, WebRequest request) {
    ExceptionBody body = ExceptionBody
        .builder()
        .message("Property value can't be nullable")
        .status(HttpStatus.NOT_ACCEPTABLE.value())
        .path(request.getDescription(true))
        .timestamp(new Date().toString())
        .build();
    return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
  }


  @ExceptionHandler(value = {RequestBodyHasNullFieldException.class})
  protected ResponseEntity<Object> handleMissingOrderParamException(RequestBodyHasNullFieldException e, WebRequest request) {
    ExceptionBody body = ExceptionBody
            .builder()
            .message(e.getMessage() + " request: " + request.toString())
            .status(406)
            .path(request.getDescription(true))
            .timestamp(new Date().toString())
            .build();
    return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
  }

  @ExceptionHandler(value = {FileTypeNotFoundException.class})
  protected ResponseEntity<Object> handleTypeNotFoundException(RuntimeException e, WebRequest request){
    ExceptionBody body = ExceptionBody
            .builder()
            .message(e.getMessage())
            .status(404)
            .path(request.getDescription(true))
            .timestamp(new Date().toString())
            .build();
    return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {ResourceNotFoundException.class})
  protected ResponseEntity<Object> handleResourceNotFoundException(RuntimeException e, WebRequest request){
    ExceptionBody body = ExceptionBody
            .builder()
            .message(e.getMessage())
            .status(404)
            .path(request.getDescription(true))
            .timestamp(new Date().toString())
            .build();
    return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {FileNotAcceptedException.class})
  protected ResponseEntity<Object> handleNotAcceptedException(RuntimeException e, WebRequest request){
    ExceptionBody body = ExceptionBody
            .builder()
            .message(e.getMessage())
            .status(406)
            .path(request.getDescription(true))
            .timestamp(new Date().toString())
            .build();
    return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
  }

  @ExceptionHandler(value = {BasketNotFoundException.class})
  protected ResponseEntity<Object> handleBasketNotFoundException(RuntimeException e, WebRequest request) {
    ExceptionBody body = ExceptionBody
            .builder()
            .message(e.getMessage())
            .status(404)
            .path(request.getDescription(true))
            .timestamp(new Date().toString())
            .build();
    return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }
}