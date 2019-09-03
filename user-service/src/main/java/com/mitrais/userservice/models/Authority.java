package com.mitrais.userservice.models;

import org.springframework.security.core.GrantedAuthority;

/**
 * Provide enumeration object for Authority purpose.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
public enum Authority implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
