package com.rover.MarsRover.infra.errors;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class HandlerExceptions {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ExceptionDataResponse>> error400(MethodArgumentNotValidException exception) {

   var errors = exception.getFieldErrors().stream().map(ExceptionDataResponse::new).toList();

   return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<String> error400(ConstraintViolationException exception) {
      System.out.println(exception.getLocalizedMessage());
      return ResponseEntity.badRequest().body(exception.getMessage());
  }

}
