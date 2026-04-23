package com.project.connect.posts_service.Exception;

public class BadRequestException  extends RuntimeException{

    public  BadRequestException(String message)
    {
        super(message);
    }
    public BadRequestException()
    {
        super("BadRequestException !!");
    }
}
