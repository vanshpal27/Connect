package com.project.connect.users_service.Service;

import com.project.connect.users_service.Dto.SingUpDto;
import com.project.connect.users_service.Dto.UserDto;
import org.apache.coyote.BadRequestException;

public interface AuthService {
    public UserDto signUp(SingUpDto singUpDto) throws BadRequestException;
}
