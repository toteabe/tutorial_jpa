package org.iesvdm.tutorial.exception;

import org.iesvdm.tutorial.domain.MensajeRespuesta;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public MensajeRespuesta exceptionHandler(Exception e) {
        return new MensajeRespuesta(e.getMessage());
    }

}
