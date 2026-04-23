package com.project.connect.posts_service.Event;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostEvent {

    private Long Id;
    private Long userId;
    private String content;

}
