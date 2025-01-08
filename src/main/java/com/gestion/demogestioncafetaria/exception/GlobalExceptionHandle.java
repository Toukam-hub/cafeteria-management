package com.gestion.demogestioncafetaria.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Model> handleException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(getMessage(exception.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<Model> handleException(ResourceAlreadyExistException exception) {
        return new ResponseEntity<>(getMessage(exception.getMessage(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthenticateException.class)
    public ResponseEntity<Model> handleException(AuthenticateException exception) {
        return new ResponseEntity<>(getMessage(exception.getMessage(), HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SomethingWrongException.class)
    public ResponseEntity<Model> handleException(SomethingWrongException exception) {
        return new ResponseEntity<>(getMessage(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Model> handleException(BadRequestException exception) {
        return new ResponseEntity<>(getMessage(exception.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Model> handleException(AccessDeniedException  exception) {
        return new ResponseEntity<>(getMessage(exception.getMessage(), HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Model> handleException(MethodArgumentNotValidException   exception) {
        return new ResponseEntity<>(getMessage(exception.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( ExpiredJwtException.class)
    public ResponseEntity<Model> handleException( ExpiredJwtException   exception) {
        return new ResponseEntity<>(getMessage(exception.getMessage(), HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }


    private Model getMessage(String message, HttpStatus httpStatus) {
        return Model.builder()
                .message(message)
                .code(httpStatus.value())
                .date(Instant.now())
                .build();
    }
}
