package com.project.connect.users_service.Service.Imple;

import com.project.connect.users_service.Dto.LogInDto;
import com.project.connect.users_service.Dto.SingUpDto;
import com.project.connect.users_service.Dto.UserDto;
import com.project.connect.users_service.Entity.User;
import com.project.connect.users_service.Exception.ResourceNotFoundException;
import com.project.connect.users_service.OpenFeignClient.ConnectionClient;
import com.project.connect.users_service.Repository.AuthRepo;
import com.project.connect.users_service.Security.JwtHelper;
import com.project.connect.users_service.Service.AuthService;
import com.project.connect.users_service.Utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImple implements AuthService {

    private final AuthRepo authRepo;

    private final ModelMapper modelMapper;

    private final  JwtHelper jwtHelper;
    private final ConnectionClient connectionClient;
    @Override
    public UserDto signUp(SingUpDto singUpDto) throws BadRequestException {
        boolean check  = authRepo.existsByEmail(singUpDto.getEmail());
        if (check)
        {
            throw  new BadRequestException("User already exist by this email");
        };
        User user = modelMapper.map(singUpDto,User.class);
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        User saved = authRepo.save(user);
        connectionClient.create(saved.getId());
        return modelMapper.map(saved, UserDto.class);


    }

    public String[] logIn(LogInDto logInDto) throws BadRequestException {
        User user = authRepo.findByEmail(logInDto.getEmail()).orElseThrow(()-> new ResourceNotFoundException("User not found with this email : " + logInDto.getEmail()));

        boolean isValid = PasswordUtil.checkPassword(logInDto.getPassword(),user.getPassword());
        if(!isValid)
        {
            throw  new BadRequestException("Password is not valid");
        }

        String accessToken =  jwtHelper.getAccessToken(user);
        String refreshToken = jwtHelper.refreshToken(user);

        String tokens[] = {accessToken,refreshToken};
        return  tokens;



    }

    public  UserDto getById(Long userId)
    {
        return  modelMapper.map(authRepo.getReferenceById(userId.longValue()), UserDto.class);
    }

    public String refreshToken(String refreshToken)
    {
        Long userId = jwtHelper.getUserId(refreshToken);
        User user = authRepo.getReferenceById(userId);
        return jwtHelper.getAccessToken(user);
    }

}
