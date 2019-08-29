package com.mitrais.userservice.controllers;

import com.mitrais.userservice.configs.JwtTokenProvider;
import com.mitrais.userservice.controllers.request.AuthBody;
import com.mitrais.userservice.exceptions.model.ServiceException;
import com.mitrais.userservice.models.dto.AuthDto;
import com.mitrais.userservice.models.dto.Response;
import com.mitrais.userservice.models.dto.UserDto;
import com.mitrais.userservice.repositories.MessageRepository;
import com.mitrais.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController implements BaseResponse<AuthDto> {
    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/login")
    public ResponseEntity login(@Valid @RequestBody AuthBody data) {
        String email = data.getEmail();
        UserDto user = userService.findUserByEmail(email);
        if (!user.getEnabled()) {
            throw new ServiceException(MessageRepository.USER_NOT_ACTIVE);
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, data.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(MessageRepository.AUTH_FAIL);
        }

        List<AuthDto> authData = new ArrayList<>();
        String token = jwtTokenProvider.createToken(user);
        authData.add(new AuthDto(email, token));
        log.info("AuthController login ", authData);
        return ok(getResponse(false, MessageRepository.AUTH_SUCCESS_CODE, MessageRepository.AUTH_SUCCESS, authData));
    }

    @Override
    public Response<AuthDto> getResponse(boolean error, String code, String message, List<AuthDto> data) {
        Response<AuthDto> response = new Response<>();
        response.setError(error);
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
