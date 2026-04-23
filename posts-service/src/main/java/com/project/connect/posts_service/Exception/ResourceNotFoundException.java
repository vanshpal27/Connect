package com.project.connect.posts_service.Exception;

public class ResourceNotFoundException extends  RuntimeException {


    public ResourceNotFoundException(String message)
    {
        super(message);
    }
    public ResourceNotFoundException()
    {
        super("ResourceNotFountException !!");
    }
}
