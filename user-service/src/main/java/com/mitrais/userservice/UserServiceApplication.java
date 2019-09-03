package com.mitrais.userservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mitrais.userservice.models.Authority;
import com.mitrais.userservice.models.Role;
import com.mitrais.userservice.repositories.RoleRepository;

//@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(RoleRepository roleRepository) {
        return args -> {
            Role adminRole = roleRepository.findByRole(Authority.ADMIN.getAuthority());
            if (adminRole == null) {
                Role newAdminRole = new Role();
                newAdminRole.setRole(Authority.ADMIN.getAuthority());
                roleRepository.save(newAdminRole);
            }

            Role userRole = roleRepository.findByRole(Authority.USER.getAuthority());
            if (userRole == null) {
                Role newUserRole = new Role();
                newUserRole.setRole(Authority.USER.getAuthority());
                roleRepository.save(newUserRole);
            }
        };
    }
}
