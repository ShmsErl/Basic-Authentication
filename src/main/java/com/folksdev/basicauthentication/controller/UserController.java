package com.folksdev.basicauthentication.controller;

import com.folksdev.basicauthentication.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/private")
public class UserController {


    @GetMapping("/user")
    String sign(@RequestParam  BindingResult result) {
        if(result.hasErrors()){
            return "login";
        }
        return "secure";
    }
/*
    @GetMapping()
    public String getUser(){

        return "sign "  ;
    }


    //@PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user")
    public String getUserRole(){

        return "Welcome to the Home Page USER Role "   ;
    }
    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String getAdminRole(){

        return "Welcome to the Home Page ADMİN Role "   ;
    }*/
}
