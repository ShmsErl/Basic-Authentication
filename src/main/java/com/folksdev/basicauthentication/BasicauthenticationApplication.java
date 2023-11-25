package com.folksdev.basicauthentication;

import com.folksdev.basicauthentication.dto.CreateUserRequest;
import com.folksdev.basicauthentication.model.Role;
import com.folksdev.basicauthentication.model.User;
import com.folksdev.basicauthentication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.plaf.PanelUI;
import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class BasicauthenticationApplication implements CommandLineRunner {

    private UserService  userService;

    public static void main(String[] args) {
        SpringApplication.run(BasicauthenticationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
    /*public void createDummyData() {

        CreateUserRequest user1 = CreateUserRequest.builder().name("Şeyhmus")
                .username("shms")
                .password("123")
                .authorities(Set.of( Role.ROLE_USER))
                .build();

        userService.createUser(user1);


        CreateUserRequest admin = CreateUserRequest.builder().name("hamit")
                .username("hmt")
                .password("aaa")
                .authorities(Set.of(Role.ROLE_ADMIN))
                .build();

        userService.createUser(admin);

    }*/

}
