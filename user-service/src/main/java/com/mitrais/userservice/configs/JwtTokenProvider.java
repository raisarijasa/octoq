package com.mitrais.userservice.configs;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mitrais.userservice.models.dto.UserDto;
import com.mitrais.userservice.services.UserServiceImpl;

/**
 * Provide JWT Token provider.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@Component
public class JwtTokenProvider {
    private static final String HEADER = "Authorization";
    private static final String BEARER = "Bearer";

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds;

    @Autowired
    private UserServiceImpl userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * Provide token creation based on User data.
     *
     * @param user data
     * @return token string
     */
    public String createToken(UserDto user) {
        Claims claims = Jwts.claims();
        claims.put("email", user.getEmail());
        claims.put("fullname", user.getFullname());
        claims.put("roles", user.getRoles());
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

    /**
     * Provide Authentication by user token.
     *
     * @param token string
     * @return Authentication
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Provide Username based on user token.
     *
     * @param token string
     * @return username
     */
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("email").toString();
    }

    /**
     * Provide token resolver to remove bearer from token.
     *
     * @param req HttpServletRequest
     * @return user token without bearer
     */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(HEADER);
        if (bearerToken != null && bearerToken.startsWith(BEARER + " ")) {
            return bearerToken.substring(BEARER.length() + 1);
        }
        return null;
    }

    /**
     * Provide token validation.
     *
     * @param token string
     * @return true/false
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("Expired or invalid JWT token");
        }
    }
}
