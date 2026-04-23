package com.project.connect.posts_service.Handler;

import com.project.connect.posts_service.Exception.BadRequestException;
import com.project.connect.posts_service.Exception.ResourceNotFoundException;
import com.project.connect.posts_service.Extra.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> HandleResourceNotFound(ResourceNotFoundException resourceNotFoundException)
    {
        ApiError apiError = ApiError.builder().message(resourceNotFoundException.getMessage()).httpStatus(HttpStatus.NOT_FOUND).build();
        return  new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> HandleBadRequestException(BadRequestException badRequestException)
    {
        ApiError apiError = ApiError.builder().message(badRequestException.getMessage()).httpStatus(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }
}
