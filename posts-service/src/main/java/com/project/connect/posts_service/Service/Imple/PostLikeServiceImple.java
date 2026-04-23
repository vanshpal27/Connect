package com.project.connect.posts_service.Service.Imple;

import com.project.connect.posts_service.ContextHandler.UserContextHandler;
import com.project.connect.posts_service.Entity.Post;
import com.project.connect.posts_service.Entity.PostLike;
import com.project.connect.posts_service.Event.PostLikeEvent;
import com.project.connect.posts_service.Exception.BadRequestException;
import com.project.connect.posts_service.Exception.ResourceNotFoundException;
import com.project.connect.posts_service.Repository.PostLikeRepo;
import com.project.connect.posts_service.Repository.PostRepo;
import com.project.connect.posts_service.Service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeServiceImple implements PostLikeService {


    private final PostLikeRepo postLikeRepo;
    private  final PostRepo postRepo;
    private final KafkaTemplate<Long,PostLikeEvent> kafkaTemplate;
    @Override
    public void likePost(Long postId) {
        Long userId = UserContextHandler.getCurrentUserId();
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found with this postId : "+postId ));
        boolean alreadyExists =  postLikeRepo.existsByPostIdAndUserId(postId,userId);
        if(alreadyExists)
        {
            throw new BadRequestException("Already Like is there can't Like again");
        }
        PostLike postLike = PostLike.builder().userId(userId).postId(postId).build();
        postLikeRepo.save(postLike);
        PostLikeEvent postLikeEvent = new PostLikeEvent();
        postLikeEvent.setLikedUserId(userId);
        postLikeEvent.setPostId(postId);
        postLikeEvent.setCreatorId(post.getUserId());
        kafkaTemplate.send("post-like-topic",postId,postLikeEvent);
        log.info("Post with this id : {} liked successfully",postId);
    }

    public  void unLikePost(Long postId)
    {
        Long userId = UserContextHandler.getCurrentUserId();
        log.info("Attempting to unlike the post with postId: {}" , postId);
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post not found with this postId : "+ postId));
        Boolean Exists = postLikeRepo.existsByPostIdAndUserId(postId, userId);
        if(!Exists)
        {
            throw new BadRequestException("Post like not exists on this post");
        }
        postLikeRepo.deleteByPostIdAndUserId(postId,userId);
        log.info("Post with this id: {} unliked successfully", postId);
    }
}
