package com.project.connect.users_service.Exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message)
    {
        super(message);
    }

    public  ResourceNotFoundException()
    {
        super("ResourceNotFoundException !!");
    }
}
