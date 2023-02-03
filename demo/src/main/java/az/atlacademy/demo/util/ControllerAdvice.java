package az.atlacademy.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(InsufficientException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void exception() {

    }
}
