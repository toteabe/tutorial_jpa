package org.iesvdm.tutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GeneralControllerAdvice {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String entityNotFoundHandler(EntityNotFoundException entityNotFoundException) {
        return entityNotFoundException.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotCouplingIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String entityNotFoundHandler(NotCouplingIdException notCouplingIdException) {
        return notCouplingIdException.getMessage();
    }

}
