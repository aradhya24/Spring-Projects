package com.aurionpro.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aurionpro.error.EmployeeErrorPageResp;
import com.aurionpro.exception.EmployeeNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<EmployeeErrorPageResp> handleEmployeeNotFound(EmployeeNotFoundException exception) {
        EmployeeErrorPageResp errorPageResp = new EmployeeErrorPageResp();
        errorPageResp.setStatus(HttpStatus.NOT_FOUND.value());
        errorPageResp.setMessage(exception.getMessage());
        errorPageResp.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorPageResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<EmployeeErrorPageResp> handleTypeMismatch(MethodArgumentTypeMismatchException exception) {
        EmployeeErrorPageResp errorPageResp = new EmployeeErrorPageResp();
        errorPageResp.setStatus(HttpStatus.BAD_REQUEST.value());
        errorPageResp.setTimestamp(System.currentTimeMillis());
        errorPageResp.setMessage("Invalid input type: Please enter a valid number for employee ID.");
        return new ResponseEntity<>(errorPageResp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<EmployeeErrorPageResp> handleValidationErrors(MethodArgumentNotValidException exception) {
        EmployeeErrorPageResp errorPageResp = new EmployeeErrorPageResp();
        errorPageResp.setStatus(HttpStatus.BAD_REQUEST.value());
        errorPageResp.setTimestamp(System.currentTimeMillis());
        errorPageResp.setMessage("Validation failed: " + exception.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(errorPageResp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<EmployeeErrorPageResp> handleGeneralException(Exception exception) {
        EmployeeErrorPageResp errorPageResp = new EmployeeErrorPageResp();
        errorPageResp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorPageResp.setTimestamp(System.currentTimeMillis());
        errorPageResp.setMessage("Something went wrong: " + exception.getMessage());
        return new ResponseEntity<>(errorPageResp, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
