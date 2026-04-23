package com.project.connect.posts_service.Repository;

import com.project.connect.posts_service.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {
    public List<Post> findAllByUserId(Long userId);

    List<Post> findByUserId(Long userId);
}
