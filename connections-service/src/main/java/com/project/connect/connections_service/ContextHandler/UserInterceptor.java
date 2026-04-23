package com.project.connect.connections_service.ContextHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor  implements HandlerInterceptor {



    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception{
        Long userId = Long.valueOf(request.getHeader("X-User-Id"));
        if(userId!=null)
        {
            UserContextHandler.setCurrentUserId(userId);
        }

        return HandlerInterceptor.super.preHandle(request,response,handler);

    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception{
        UserContextHandler.clearCurrentUserId();

    }
}
