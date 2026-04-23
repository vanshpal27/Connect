package com.project.connect.users_service.Repository;

import com.project.connect.users_service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepo extends JpaRepository<User,Long> {

   Optional<User> findByEmail(String email);
   Boolean existsByEmail(String email);
}
