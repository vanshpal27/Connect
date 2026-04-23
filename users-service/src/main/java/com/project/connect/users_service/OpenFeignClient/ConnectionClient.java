package com.project.connect.users_service.OpenFeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "connections-service",path = "/connections",url = "${CONNECTIONS_SERVICE_URL:}")
public interface ConnectionClient {

    @PostMapping("/core")
    public  void create(@RequestHeader("X-User-Id") Long userId);
}
