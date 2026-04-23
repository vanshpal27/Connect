package com.project.connect.posts_service.Service;

import org.springframework.transaction.annotation.Transactional;

public interface PostLikeService {


    public  void likePost(Long postId);
    @Transactional
    public void unLikePost(Long postId);

}
