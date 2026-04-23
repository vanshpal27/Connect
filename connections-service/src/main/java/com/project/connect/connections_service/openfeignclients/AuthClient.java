package com.project.connect.connections_service.openfeignclients;

import com.project.connect.connections_service.Entity.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-service",path = "/users",url = "${USER_SERVICE_URL:}")
public interface AuthClient {

    @GetMapping("/core/{userId}")
    public  UserDto getById(@PathVariable Long userId) ;
}
