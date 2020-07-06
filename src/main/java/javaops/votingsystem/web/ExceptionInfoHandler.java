package javaops.votingsystem.web;

import javaops.votingsystem.util.exception.IllegalRequestDataException;
import javaops.votingsystem.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.BindException;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, IllegalRequestDataException.class, NotFoundException.class})
    protected void handleValidationError(Exception e) {
        log.error(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected void conflict(DataIntegrityViolationException e) {
        log.error(e.getMessage());
    }
}
