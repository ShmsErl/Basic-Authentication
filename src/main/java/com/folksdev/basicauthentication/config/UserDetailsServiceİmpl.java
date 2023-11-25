package com.folksdev.basicauthentication.config;

import com.folksdev.basicauthentication.model.User;
import com.folksdev.basicauthentication.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceİmpl implements UserDetailsService {

    public final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userService.findByUserName(username);
        return user.orElseThrow( EntityNotFoundException::new);
    }
}
