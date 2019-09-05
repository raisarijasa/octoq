package com.mitrais.userservice.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mitrais.userservice.exceptions.model.UserNotFoundException;
import com.mitrais.userservice.models.Role;
import com.mitrais.userservice.models.User;
import com.mitrais.userservice.models.dto.UserDto;
import com.mitrais.userservice.repositories.MessageRepository;
import com.mitrais.userservice.repositories.RoleRepository;
import com.mitrais.userservice.repositories.UserRepository;

/**
 * Provide implementation of functionality to manipulate User data.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public UserDto findUserByEmail(String email) {
        UserDto userDto = new UserDto();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(String.format(messageRepository.USER_WITH_EMAIL_NOT_FOUND, email));
        }
        BeanUtils.copyProperties(user, userDto, "roles", "password");
        Set<String> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRole());
        }
        userDto.setRoles(roles);
        return userDto;
    }

    @Override
    public void saveUser(User user) {
        boolean isEnabled = false;
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else {
            isEnabled = user.isEnabled();
        }
        user.setEnabled(isEnabled);
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(roleRepository.findByRole(role.getRole()));
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(String.format(messageRepository.USER_WITH_EMAIL_NOT_FOUND, email));
        }
        userRepository.delete(user);
    }

    @Override
    public boolean isOldPasswordValid(String encryptedPassword, String oldPassword) {
        return bCryptPasswordEncoder.matches(oldPassword, encryptedPassword);
    }

    @Override
    public void changeUserPassword(String email, String passwordNew, String passwordOld) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(messageRepository.USER_NOT_FOUND);
        }
        if (!isOldPasswordValid(user.getPassword(), passwordOld)) {
            throw new BadCredentialsException(messageRepository.INVALID_OLD_PASSWORD);
        }
        user.setPassword(bCryptPasswordEncoder.encode(passwordNew));
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        User userDb = userRepository.findByEmail(user.getEmail());
        if (userDb == null) {
            throw new UserNotFoundException(String.format(messageRepository.USER_WITH_EMAIL_NOT_FOUND, user.getEmail()));
        }
        userDb.setEmail(user.getEmail());
        userDb.setFullname(user.getFullname());
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(roleRepository.findByRole(role.getRole()));
        }
        userDb.setRoles(roles);
        userRepository.save(userDb);
    }

    @Override
    public void enableUser(User user) {
        User userDb = userRepository.findByEmail(user.getEmail());
        if (userDb == null) {
            throw new UserNotFoundException(String.format(messageRepository.USER_WITH_EMAIL_NOT_FOUND, user.getEmail()));
        }
        userDb.setEnabled(user.isEnabled());
        userRepository.save(userDb);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException(String.format(messageRepository.USER_WITH_EMAIL_NOT_FOUND, email));
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
