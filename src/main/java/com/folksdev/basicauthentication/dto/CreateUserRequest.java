package com.folksdev.basicauthentication.dto;


import com.folksdev.basicauthentication.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Builder
@Getter
@Setter
public class CreateUserRequest
{

    private String name;
    private  String username;
    private String password;
    private Set<Role> authorities;



}
