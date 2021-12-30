package ai.exscientia.compounds.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class CompoundNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CompoundNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String compoundNotFoundHandler(CompoundNotFoundException ex) {
        return ex.getMessage();
    }
}
