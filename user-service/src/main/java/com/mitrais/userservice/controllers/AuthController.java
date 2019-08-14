package com.mitrais.userservice.controllers;

import com.mitrais.userservice.configs.JwtTokenProvider;
import com.mitrais.userservice.exceptions.model.ServiceException;
import com.mitrais.userservice.models.User;
import com.mitrais.userservice.models.dto.AuthBody;
import com.mitrais.userservice.models.dto.AuthResponse;
import com.mitrais.userservice.models.dto.Response;
import com.mitrais.userservice.repositories.MessageRepository;
import com.mitrais.userservice.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/auth")
public class AuthController implements BaseResponse<AuthResponse> {
    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserServiceImpl userService;

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AuthBody data) {
        try {
            String email = data.getEmail();
            User user = userService.findUserByEmail(email);
            if (!user.isEnabled()) {
                throw new ServiceException(MessageRepository.USER_NOT_ACTIVE, "AuthController");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, data.getPassword()));
            List<AuthResponse> authData = new ArrayList<>();
            String token = jwtTokenProvider.createToken(user);
            authData.add(new AuthResponse(email, token));
            log.info("AuthController login ", authData);
            return ok(getResponse(false, MessageRepository.AUTH_SUCCESS_CODE, MessageRepository.AUTH_SUCCESS, authData));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(MessageRepository.AUTH_FAIL);
        }
    }

    @Override
    public Response<AuthResponse> getResponse(boolean error, String code, String message, List<AuthResponse> data) {
        Response<AuthResponse> response = new Response<>();
        response.setError(error);
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
