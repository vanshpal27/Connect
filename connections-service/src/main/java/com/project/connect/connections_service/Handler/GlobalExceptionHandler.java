package com.project.connect.connections_service.Handler;

import com.project.connect.connections_service.Exception.ResourceNotFound;
import com.project.connect.connections_service.Extra.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiError>  handleResourceNotFound(ResourceNotFound resourceNotFound)
    {
        ApiError apiError = new ApiError();
        apiError.setMessage(resourceNotFound.getMessage());
        apiError.setHttpStatus(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }
}
