package com.project.connect.notifications_service.openfeignclient;

import com.project.connect.notifications_service.Entity.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
@FeignClient(name = "connections-service",path = "/connections",url = "${CONNECTIONS_SERVICE_URL:}")
public interface ConnectionClient {

    @GetMapping("/core/firstDegree")
    List<PersonDto> getAllFirstDegreeConnection(@RequestHeader("X-User-Id")Long userId);

}
