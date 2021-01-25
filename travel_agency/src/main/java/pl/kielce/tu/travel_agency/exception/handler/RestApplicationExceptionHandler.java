package pl.kielce.tu.travel_agency.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.kielce.tu.travel_agency.exception.RegistrationException;

import javax.validation.ValidationException;

@ControllerAdvice
public class RestApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RegistrationException.class, ValidationException.class})
    public ResponseEntity<?> handleRegistrationException(Exception e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), null, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}
