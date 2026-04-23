package com.project.connect.connections_service.Extra;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiError{

    private String message;
    private HttpStatus httpStatus;

}
