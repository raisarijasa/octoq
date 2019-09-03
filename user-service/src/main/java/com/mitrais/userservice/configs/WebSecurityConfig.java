package com.mitrais.userservice.configs;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.mitrais.userservice.repositories.MessageRepository;
import com.mitrais.userservice.services.UserServiceImpl;

/**
 * Provide configuration security.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * Provide authentication manager builder configuration.
     *
     * @param auth authentication manager
     * @throws Exception error
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * Provide Http Security configuration.
     *
     * @param http http security
     * @throws Exception error
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests()
//                .antMatchers("/api/auth/login").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/api/user**").hasAuthority(Authority.ADMIN.getAuthority())
//                .antMatchers(HttpMethod.PUT, "/api/user/activation").hasAuthority(Authority.ADMIN.getAuthority())
//                .anyRequest().authenticated().and()
                .csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint()).and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

    /**
     * Provide web security configuration.
     *
     * @param web web security
     * @throws Exception error
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    /**
     * Provide password encoder.
     *
     * @return password encoder
     */
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provide authentication manager.
     *
     * @return authentication manager
     * @throws Exception error
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Provide authentication entry point.
     *
     * @return unauthorized entry point
     */
    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized");
    }

    /**
     * Provide user details.
     *
     * @return user detail service.
     */
    @Bean
    public UserDetailsService mongoUserDetails() {
        return new UserServiceImpl();
    }

    @Bean
    public MessageRepository defaultMessageRepository() {
        return new MessageRepository();
    }
}
