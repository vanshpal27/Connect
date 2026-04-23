package com.project.connect.notifications_service.Consumer;

import com.project.connect.notifications_service.Entity.Notification;
import com.project.connect.notifications_service.Entity.PersonDto;
import com.project.connect.notifications_service.Repository.NotificationRepo;
import com.project.connect.notifications_service.openfeignclient.ConnectionClient;
import com.project.connect.notifications_service.service.SendNotification;
import com.project.connect.posts_service.Event.PostEvent;
import com.project.connect.posts_service.Event.PostLikeEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceConsumer {

    private final SendNotification sendNotification;
    private final ConnectionClient connectionClient;
    @KafkaListener(topics = "post-create-topic")
    public void handlePostCreate(PostEvent postEvent)
    {
        Long userId = postEvent.getUserId();
        List<PersonDto> personDtoList =  connectionClient.getAllFirstDegreeConnection(userId);

        for(PersonDto personDto : personDtoList)
        {
            String message = "Hey user with id : "+personDto.getUserId()+" has posted a post go check out";
            sendNotification.send(message,userId);
        }

    }




    @KafkaListener(topics = "post-like-topic")
    public void handlePostlike(PostLikeEvent postLikeEvent)
    {
       String message = "Hey user with id "+postLikeEvent.getLikedUserId()+"has liked your post with id "+postLikeEvent.getPostId();
        sendNotification.send(message, postLikeEvent.getLikedUserId());
    }

}
