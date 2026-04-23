package com.project.connect.notifications_service.Repository;

import com.project.connect.notifications_service.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification,Long> {
}
