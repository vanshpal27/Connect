package com.project.connect.posts_service.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PostLikeDto {

    private  Long  id;

    private  Long userId;

    private Long postId;
    
    private LocalDateTime createdAt;
}
