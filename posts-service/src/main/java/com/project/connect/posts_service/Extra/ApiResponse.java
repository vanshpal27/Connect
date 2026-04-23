package com.project.connect.posts_service.Extra;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
public class ApiResponse<T>{

    private T body;
    private ApiError apiError;
    private LocalDateTime creationTime;

    public ApiResponse()
    {
        this.creationTime = LocalDateTime.now();
    }
    public ApiResponse(T body)
    {
        this();
       this.body = body;
    }
    public ApiResponse(ApiError apiError)
    {
        this();
        this.apiError = apiError;
    }

}
