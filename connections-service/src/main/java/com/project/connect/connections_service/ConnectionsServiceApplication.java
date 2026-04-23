package com.project.connect.connections_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
@EnableFeignClients
public class ConnectionsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectionsServiceApplication.class, args);
	}

}
