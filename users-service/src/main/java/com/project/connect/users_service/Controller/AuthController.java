package com.project.connect.users_service.Controller;

import com.project.connect.users_service.Dto.LogInDto;
import com.project.connect.users_service.Dto.SingUpDto;
import com.project.connect.users_service.Dto.UserDto;
import com.project.connect.users_service.Service.Imple.AuthServiceImple;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
public class AuthController {

    private  final AuthServiceImple authServiceImple;
    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SingUpDto singUpDto) throws BadRequestException {
        return new ResponseEntity<>(authServiceImple.signUp(singUpDto), HttpStatus.CREATED);
    }

    @PostMapping("/logIn")
    public   ResponseEntity<String> logIn(@RequestBody LogInDto logInDto, HttpServletResponse httpServletResponse) throws BadRequestException {
        String[] tokens = authServiceImple.logIn(logInDto);

        Cookie cookie = new Cookie("refreshToken",tokens[1]);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);

        return new ResponseEntity<>(tokens[0],HttpStatus.OK);



    }

    @GetMapping("/{userId}")
    public UserDto getById(@PathVariable Long userId)
    {
        return authServiceImple.getById(userId);
    }

    @GetMapping("/refresh")
    private ResponseEntity<String> refresh(HttpServletRequest httpServletRequest) throws AuthenticationException {

        String refreshToken = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(()-> new AuthenticationException("RefreshToken is not valid"));

         return new ResponseEntity<>(authServiceImple.refreshToken(refreshToken),HttpStatus.OK);
    }
}
