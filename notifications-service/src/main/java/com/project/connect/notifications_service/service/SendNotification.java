package com.project.connect.notifications_service.service;

import com.project.connect.notifications_service.Entity.Notification;
import com.project.connect.notifications_service.Repository.NotificationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendNotification
{

    private final NotificationRepo notificationRepo;
    public  void  send(String message,Long userId)
    {
        Notification notification = Notification.builder()
                .message(message)
                .userId(userId)
                .build();
        notificationRepo.save(notification);
    }
}
