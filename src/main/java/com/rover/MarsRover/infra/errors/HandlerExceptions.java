package com.rover.MarsRover.infra.errors;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class HandlerExceptions {

    public ResponseEntity<ErrorResponse> exceptionHandler(RuntimeException exception, WebRequest request, HttpStatus httpStatus) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, httpStatus);
    }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ExceptionDataResponse>> handlerArgumentInvalidException(MethodArgumentNotValidException exception) {

   var errors = exception.getFieldErrors().stream().map(ExceptionDataResponse::new).toList();

   return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handlerCommandInvalidException(ConstraintViolationException exception, WebRequest request) {
      return exceptionHandler(exception, request, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(InitialValidationsExceptions.class)
  public ResponseEntity<ErrorResponse> handlerInitialValidationException(InitialValidationsExceptions exception, WebRequest request) {
      return exceptionHandler(exception, request, HttpStatus.BAD_REQUEST) ;
  }
  @ExceptionHandler(IntegrityValidationException.class)
  public ResponseEntity<ErrorResponse> handlerIntegrityValidationException(IntegrityValidationException exception, WebRequest request) {
     return exceptionHandler(exception, request, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BehaviorValidationException.class)
  public ResponseEntity<ErrorResponse> handlerBehaviorValidationException(BehaviorValidationException exception, WebRequest request) {
    return exceptionHandler(exception, request, HttpStatus.EXPECTATION_FAILED);
  }


}
