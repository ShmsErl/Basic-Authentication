package com.folksdev.basicauthentication.service;

import com.folksdev.basicauthentication.config.PasswordEncoderConfig;
import com.folksdev.basicauthentication.dto.CreateUserRequest;
import com.folksdev.basicauthentication.model.Role;
import com.folksdev.basicauthentication.model.User;
import com.folksdev.basicauthentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoderConfig passwordEncoderConfig;


    public Optional<User> findByUserName(String userName) {

        return this.userRepository.findByUsername(userName);
    }

    public void createUser(CreateUserRequest createUserRequest) {

        User user = User.builder()
                .name(createUserRequest.getName())
                .username(createUserRequest.getUsername())
                .password(passwordEncoderConfig.bCryptPasswordEncoder().encode(createUserRequest.getPassword()))
                .authorities(Set.of(Role.ROLE_USER))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true).build();

            this.userRepository.save(user);


    }

    public void checkLogin(String userName , String password) throws Exception {

            User  user = this.userRepository.findByUsername(userName).orElseThrow(()-> new Exception("User not found"));

            boolean result = user.getPassword().equals(passwordEncoderConfig.bCryptPasswordEncoder().encode(password));

            if(!result){
                throw new Exception("PASSWORD NOT VALİD");
            }
    }


}
