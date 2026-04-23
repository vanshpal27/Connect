package com.project.connect.users_service.Extra;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse<T> {

    private T body;
    private ApiError apiError;
    private LocalDateTime localDateTime;

    public ApiResponse()
    {
        this.localDateTime = LocalDateTime.now();
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
