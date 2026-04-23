package com.project.connect.users_service.Extra;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private String message;
    private HttpStatus httpStatus;
}
