package com.project.connect.posts_service.Event;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostLikeEvent {

    private  Long postId;
    private Long creatorId;
    private  Long likedUserId;
}
