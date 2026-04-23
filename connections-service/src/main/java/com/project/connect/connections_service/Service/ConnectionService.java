package com.project.connect.connections_service.Service;

import com.project.connect.connections_service.ContextHandler.UserContextHandler;
import com.project.connect.connections_service.Entity.Person;
import com.project.connect.connections_service.Entity.UserDto;
import com.project.connect.connections_service.Exception.ResourceNotFound;
import com.project.connect.connections_service.Repository.PersonRepo;
import com.project.connect.connections_service.event.AcceptConnectionRequestEvent;
import com.project.connect.connections_service.event.RejectConnectionRequestEvent;
import com.project.connect.connections_service.event.SendConnectionRequestEvent;
import com.project.connect.connections_service.openfeignclients.AuthClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionService {

    private final PersonRepo personRepo;
    private final KafkaTemplate<Long, AcceptConnectionRequestEvent>  acceptConnectionRequestEventKafkaTemplate;
    private final KafkaTemplate<Long, SendConnectionRequestEvent> sendConnectionRequestEventKafkaTemplate;
    private final KafkaTemplate<Long, RejectConnectionRequestEvent> rejectConnectionRequestEventKafkaTemplate;
    private  final  AuthClient authClient;
    public Person getByName(String name)  {
        return  personRepo.getByName(name).orElseThrow(()-> new ResourceNotFound());
    }

    public  List<Person> getFirstDegreeConnection(Long userId)
    {

        return  personRepo.getFirstDegreeConnection(userId);
    }


    public boolean sendRequest(Long receiverUserId) {
        Long senderUserId = UserContextHandler.getCurrentUserId();
        if(senderUserId == receiverUserId)
        {
            throw new RuntimeException("Oops the senderId "+senderUserId+" and "+ "receiverId "+receiverUserId +"is same");
        }
        log.info("Creating request for sender with Id :{} , receiver with Id :{}",senderUserId,receiverUserId);
        if(personRepo.checkRequestExists(senderUserId,receiverUserId))
        {
            throw new RuntimeException("Hey request is already sent");
        }
        if(personRepo.checkConnectionExists(senderUserId,receiverUserId))
        {
            throw  new RuntimeException("Hey connections already exists");
        }
        SendConnectionRequestEvent sendConnectionRequestEvent = SendConnectionRequestEvent.builder()
                .receiverUserId(receiverUserId)
                .senderUserId(senderUserId).build();
        sendConnectionRequestEventKafkaTemplate.send("send-connection-request-topic",sendConnectionRequestEvent);

        personRepo.addConnectionRequest(senderUserId,receiverUserId);
        return  true;
    }

    public  boolean acceptRequest(Long senderUserId)
    {
        Long receiverUserId = UserContextHandler.getCurrentUserId();
        if(!personRepo.checkRequestExists(senderUserId,receiverUserId))
        {
            throw new RuntimeException("Hey request is not sent ");
        }
        personRepo.acceptRequest(senderUserId,receiverUserId);
        AcceptConnectionRequestEvent acceptConnectionRequestEvent = AcceptConnectionRequestEvent.builder()
                .receiverUserId(receiverUserId)
                .senderUserId(senderUserId)
                .build();

        acceptConnectionRequestEventKafkaTemplate.send("accept-connection-request-topic",acceptConnectionRequestEvent);
        return  true;

    }

    public boolean rejectRequest(Long senderUserId)
    {
        Long receiverUserId = UserContextHandler.getCurrentUserId();
        if(!personRepo.checkRequestExists(senderUserId,receiverUserId))
        {
            throw new RuntimeException("Hey request is not sent ");
        }
        personRepo.rejectRequest(senderUserId,receiverUserId);
        RejectConnectionRequestEvent rejectConnectionRequestEvent = RejectConnectionRequestEvent.builder().receiverUserId(receiverUserId).senderUserId(senderUserId).build();
        rejectConnectionRequestEventKafkaTemplate.send("reject-connection-request-topic",rejectConnectionRequestEvent);
        return  true;

    }

    public void create(Long userId)
    {

         UserDto userDto = authClient.getById(userId);
         Person  person = new Person();
         person.setUserId(userId);
         person.setName(userDto.getName());
         personRepo.save(person);

    }
}
