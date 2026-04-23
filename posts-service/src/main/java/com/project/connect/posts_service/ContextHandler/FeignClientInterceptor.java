package com.project.connect.posts_service.ContextHandler;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    // this is for directly passing the value to the original method of the openfeign client no need
    // of passing value through function of open feign client made class which is in this connection client class ans its function which are using no need of defining out parameter if it exists also
    // through interceptor we are passing out the value to the  method of the original class
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Long userId = UserContextHandler.getCurrentUserId();
        if(userId!=null) {
            requestTemplate.header("X-User-Id",String.valueOf(userId));
        }
    }
}
