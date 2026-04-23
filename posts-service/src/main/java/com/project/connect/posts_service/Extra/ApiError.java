package com.project.connect.posts_service.Extra;

import lombok.*;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ApiError {

    private String message;
    private HttpStatus httpStatus;

}
