package com.project.connect.posts_service.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long  id;

    @Column(nullable = false)
    private  Long userId;

    @Column(nullable = false)
    private Long postId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
