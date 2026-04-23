package com.project.connect.posts_service.OpenFeignClients;

import com.project.connect.posts_service.Dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "connections-service",path = "/connections",url = "${CONNECTIONS_SERVICE_URL:}")
public interface ConnectionClient {

    @GetMapping("/core/firstDegree")
    List<PersonDto> getAllFirstDegreeConnection();

}
