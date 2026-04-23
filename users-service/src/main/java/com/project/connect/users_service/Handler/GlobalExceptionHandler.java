package com.project.connect.users_service.Handler;

import com.project.connect.users_service.Exception.ResourceNotFoundException;
import com.project.connect.users_service.Extra.ApiError;
import com.project.connect.users_service.Extra.ApiResponse;
import feign.FeignException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse<?> handleResourceNotFound(ResourceNotFoundException resourceNotFoundException)
    {
        ApiError apiError = ApiError.builder().message(resourceNotFoundException.getMessage()).httpStatus(HttpStatus.NOT_FOUND).build();
        return new ApiResponse<>(apiError);
    }
    @ExceptionHandler(JwtException.class)
    public ApiResponse<?> handleJwtExceptionFound(JwtException jwtException)
    {
        ApiError apiError = ApiError.builder().message(jwtException.getMessage()).httpStatus(HttpStatus.NOT_FOUND).build();
        return new ApiResponse<>(apiError);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ApiResponse<?> handleJwtExceptionFound(AuthenticationException authenticationException)
    {
        ApiError apiError = ApiError.builder().message(authenticationException.getMessage()).httpStatus(HttpStatus.NOT_FOUND).build();
        return new ApiResponse<>(apiError);
    }
    @ExceptionHandler(FeignException.BadRequest.class)
    public ApiResponse<?> handleJwtExceptionFound(FeignException.BadRequest badRequest)
    {
        ApiError apiError = ApiError.builder().message(badRequest.getMessage()).httpStatus(HttpStatus.NOT_FOUND).build();
        return new ApiResponse<>(apiError);
    }
}
