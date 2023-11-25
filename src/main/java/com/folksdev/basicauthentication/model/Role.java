package com.folksdev.basicauthentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
@Getter
@AllArgsConstructor
public enum Role implements GrantedAuthority {

    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER"),
    ROLE_MOD("MOD"),
    ROLE_FSK("FSK");

    private String value;


    @Override
    public String getAuthority() {
        return name();
    }
}
