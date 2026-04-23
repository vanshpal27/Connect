package com.project.connect.connections_service.Controller;

import com.project.connect.connections_service.ContextHandler.UserContextHandler;
import com.project.connect.connections_service.Entity.Person;
import com.project.connect.connections_service.Service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;

    @GetMapping("/byName/{name}")
    public ResponseEntity<Person> getByName(@PathVariable String name)
    {
        return  new ResponseEntity<>(connectionService.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/firstDegree")
    public  ResponseEntity<List<Person>> getAllFirstDegreeConnection(){
        Long userId = UserContextHandler.getCurrentUserId();
        return  new ResponseEntity<>(connectionService.getFirstDegreeConnection(userId),HttpStatus.OK);
    }

    @PostMapping("/sendRequest/{receiverUserId}")
    public boolean sendRequest(@PathVariable Long receiverUserId)
    {
        return connectionService.sendRequest(receiverUserId);
    }

    @PostMapping("/acceptRequest/{senderUserId}")
    public boolean acceptRequest(@PathVariable Long senderUserId)
    {
        return  connectionService.acceptRequest(senderUserId);
    }

    @DeleteMapping("/rejectRequest/{senderUserId}")
    public  boolean rejectRequest(@PathVariable Long senderUserId)
    {
        return  connectionService.rejectRequest(senderUserId);
    }

    @PostMapping
    public  void create()
    {
        Long userId = UserContextHandler.getCurrentUserId();
        connectionService.create(userId);
    }
}
