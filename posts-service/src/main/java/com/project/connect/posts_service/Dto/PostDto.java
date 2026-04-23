package com.project.connect.posts_service.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PostDto {

    private Long id;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
}
