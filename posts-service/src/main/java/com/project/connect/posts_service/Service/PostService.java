package com.project.connect.posts_service.Service;

import com.project.connect.posts_service.Dto.PostDto;
import com.project.connect.posts_service.Dto.PostRequestDto;

import java.util.List;

public interface PostService {

    public PostDto create(PostRequestDto postRequestDto,Long userId);
    public  PostDto getPostById(Long postId);

    public List<PostDto> getAllPost(Long userId);
}
