package com.project.connect.posts_service.Event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class PostEvent {

    private Long Id;
    private Long userId;
    private String content;

}
