package com.project.connect.notifications_service.Consumer;

import com.project.connect.connections_service.event.AcceptConnectionRequestEvent;
import com.project.connect.connections_service.event.RejectConnectionRequestEvent;
import com.project.connect.connections_service.event.SendConnectionRequestEvent;
import com.project.connect.notifications_service.Repository.NotificationRepo;
import com.project.connect.notifications_service.service.SendNotification;
import jdk.dynalink.NamedOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConnectionServiceConsumer {

    private  final SendNotification sendNotification;

    @KafkaListener(topics = "send-connection-request-topic")
    public  void handleSendRequest(SendConnectionRequestEvent sendConnectionRequestEvent)
    {
        String message = "Hey you have an connection request from user with id : "+ sendConnectionRequestEvent.getSenderUserId();
        sendNotification.send(message,sendConnectionRequestEvent.getReceiverUserId());
    }

    @KafkaListener(topics = "accept-connection-request-topic")
    public  void  handleAcceptRequest(AcceptConnectionRequestEvent acceptConnectionRequestEvent)
    {
        String message = "Hey user with id : " +acceptConnectionRequestEvent.getReceiverUserId() +" has accepted your connection request";
        sendNotification.send(message,acceptConnectionRequestEvent.getSenderUserId());
    }

    @KafkaListener(topics = "reject-connection-request-topic")
    public void handleRejectRequest(RejectConnectionRequestEvent rejectConnectionRequestEvent)
    {
        String message = "Hey user with id : "+rejectConnectionRequestEvent.getReceiverUserId() +" has rejected your connection request";
        sendNotification.send(message,rejectConnectionRequestEvent.getSenderUserId());
    }
}
