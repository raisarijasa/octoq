package com.mitrais.userservice.services;

import com.mitrais.userservice.exceptions.model.UserNotFoundException;
import com.mitrais.userservice.models.Role;
import com.mitrais.userservice.models.User;
import com.mitrais.userservice.models.dto.UserDto;
import com.mitrais.userservice.repositories.MessageRepository;
import com.mitrais.userservice.repositories.RoleRepository;
import com.mitrais.userservice.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto findUserByEmail(String email) {
        UserDto userDto = new UserDto();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(String.format(MessageRepository.USER_WITH_EMAIL_NOT_FOUND, email));
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
    public void saveUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user, "enabled", "roles");
        boolean isEnabled = false;
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else {
            isEnabled = userDto.getEnabled();
        }
        user.setEnabled(isEnabled);
        Set<Role> roles = new HashSet<>();
        for (String role : userDto.getRoles()) {
            roles.add(roleRepository.findByRole(role));
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(String.format(MessageRepository.USER_WITH_EMAIL_NOT_FOUND, email));
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
            throw new UserNotFoundException(MessageRepository.USER_NOT_FOUND);
        }
        if (!isOldPasswordValid(user.getPassword(), passwordOld)) {
            throw new BadCredentialsException(MessageRepository.INVALID_OLD_PASSWORD);
        }
        user.setPassword(bCryptPasswordEncoder.encode(passwordNew));
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user == null) {
            throw new UserNotFoundException(String.format(MessageRepository.USER_WITH_EMAIL_NOT_FOUND, userDto.getEmail()));
        }
        user.setEmail(userDto.getEmail());
        user.setFullname(userDto.getFullname());
        Set<Role> roles = new HashSet<>();
        for (String role : userDto.getRoles()) {
            roles.add(roleRepository.findByRole(role));
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void enableUser(UserDto userDto) {
        User userExists = userRepository.findByEmail(userDto.getEmail());
        if (userExists == null) {
            throw new UserNotFoundException(String.format(MessageRepository.USER_WITH_EMAIL_NOT_FOUND, userDto.getEmail()));
        }
        userExists.setEnabled(userDto.getEnabled());
        userRepository.save(userExists);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException(String.format(MessageRepository.USER_WITH_EMAIL_NOT_FOUND, email));
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
