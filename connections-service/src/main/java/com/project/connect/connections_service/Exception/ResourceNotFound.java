package com.project.connect.connections_service.Exception;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String message)
    {
        super(message);
    }
    public ResourceNotFound()
    {
        super("ResourceNotFound");
    }
}
