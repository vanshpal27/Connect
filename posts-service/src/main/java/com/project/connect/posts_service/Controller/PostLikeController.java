package com.project.connect.posts_service.Controller;

import com.project.connect.posts_service.Service.Imple.PostLikeServiceImple;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postLike")
@RequiredArgsConstructor
public class PostLikeController {

    private final PostLikeServiceImple postLikeServiceImple;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId)
    {
        postLikeServiceImple.likePost(postId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public  ResponseEntity<Void> unLikePost(@PathVariable Long postId)
    {
        postLikeServiceImple.unLikePost(postId);
        return  ResponseEntity.noContent().build();
    }
}
