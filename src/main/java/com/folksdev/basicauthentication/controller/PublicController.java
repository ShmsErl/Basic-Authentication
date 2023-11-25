package com.folksdev.basicauthentication.controller;

import com.folksdev.basicauthentication.dto.CreateUserRequest;
import com.folksdev.basicauthentication.model.User;
import com.folksdev.basicauthentication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor

public class PublicController {

    public final UserService userService;

    @GetMapping
    public String homePage(Model model){
        model.addAttribute("name","Merhaba ,Anasayfaya hoşgeldin ");
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegisterPage(@ModelAttribute("user") CreateUserRequest user, BindingResult result, Model model) {
        model.addAttribute("user", user);

        if (result.hasErrors()) {
            return "register";
        } else {
            userService.createUser(user);

        }
        return "index";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/index")
    public String index2() {
        return "index";
    }



    @RequestMapping("/secure")
    public String secure() {
        return "secure";
    }







   /* @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkLogin(@ModelAttribute("user") String username ,String password , BindingResult result, Model model) throws Exception {
        model.addAttribute( username,password);

        this.userService.checkLogin(username,password);



        return "secure";
    }*/


    }
