package com.project.connect.connections_service.ContextHandler;

import org.springframework.stereotype.Component;

@Component
public class UserContextHandler {

    private  static  final ThreadLocal<Long> currentUserId = new ThreadLocal<>();

     public static  Long getCurrentUserId()
     {
         return currentUserId.get();
     }
     // Package specific
     static  void setCurrentUserId(Long userId)
     {
         currentUserId.set(userId);
     }

     static  void  clearCurrentUserId()
     {
         currentUserId.remove();
     }

}
