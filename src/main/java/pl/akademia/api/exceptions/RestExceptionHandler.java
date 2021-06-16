package pl.akademia.api.exceptions;

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
    String param = request.getParameter("serial");
    ExceptionBody body = ExceptionBody
        .builder()
        .message(e.getMessage() + " serialNumber: " + param)
        .status(404)
        .path(request.getDescription(true))
        .timestamp(new Date().toString())
        .build();
    return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }
}