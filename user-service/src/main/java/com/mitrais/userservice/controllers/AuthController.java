package com.mitrais.userservice.controllers;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

import com.mitrais.userservice.configs.JwtTokenProvider;
import com.mitrais.userservice.controllers.request.AuthRequest;
import com.mitrais.userservice.exceptions.model.ServiceException;
import com.mitrais.userservice.models.dto.AuthDto;
import com.mitrais.userservice.models.dto.UserDto;
import com.mitrais.userservice.repositories.MessageRepository;
import com.mitrais.userservice.services.UserService;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Provide functionality for User Authentication.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController<AuthDto> {
    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MessageRepository messageRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, MessageRepository messageRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.messageRepository = messageRepository;
    }

    /**
     * Provide functionality to login user.
     *
     * @param data authentication data
     * @return response entity
     */
    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/login")
    public ResponseEntity login(@Valid @RequestBody AuthRequest data) {
        String email = data.getEmail();
        UserDto user = userService.findUserByEmail(email);
        if (!user.getEnabled()) {
            throw new ServiceException(messageRepository.USER_NOT_ACTIVE);
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, data.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(messageRepository.AUTH_FAIL);
        }

        List<AuthDto> authData = new ArrayList<>();
        String token = jwtTokenProvider.createToken(user);
        authData.add(new AuthDto(email, token));
        log.info("AuthController login ", authData);
        return ok(getResponse(false, messageRepository.AUTH_SUCCESS_CODE, messageRepository.AUTH_SUCCESS, authData));
    }
}
