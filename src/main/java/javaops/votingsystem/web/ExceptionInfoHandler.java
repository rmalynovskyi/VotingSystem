package javaops.votingsystem.web;

import javaops.votingsystem.util.exception.IllegalRequestDataException;
import javaops.votingsystem.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, IllegalRequestDataException.class, NotFoundException.class})
    protected ResponseEntity<Object> handleValidationError(Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> conflict(DataIntegrityViolationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
