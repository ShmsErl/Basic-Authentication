package com.folksdev.basicauthentication.security;

import com.folksdev.basicauthentication.model.Role;
import jakarta.servlet.FilterChain;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {
        MvcRequestMatcher.Builder mvcRequestMatcher = new MvcRequestMatcher.Builder(handlerMappingIntrospector);
        http
                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(csrfConfig -> csrfConfig.ignoringRequestMatchers(mvcRequestMatcher.pattern("/public/**"))
                        .ignoringRequestMatchers(PathRequest.toH2Console()))
                .formLogin(form -> form.loginPage("/login").permitAll()
                        .defaultSuccessUrl("/secure"))
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout"))

                .authorizeHttpRequests(x ->
                        x
                                .requestMatchers(mvcRequestMatcher.pattern("/")).permitAll()
                                .requestMatchers(mvcRequestMatcher.pattern("/register")).permitAll()
                                .requestMatchers(mvcRequestMatcher.pattern("/webjars/**")).permitAll()
                                .requestMatchers(mvcRequestMatcher.pattern("/webjars/")).permitAll()
                                .requestMatchers(mvcRequestMatcher.pattern("/index")).permitAll()
                                //.requestMatchers(mvcRequestMatcher.pattern("/private/secure")).hasAnyRole(Role.ROLE_USER.getValue(),
                                //  Role.ROLE_ADMIN.getValue(),
                                // Role.ROLE_MOD.getValue(),
                                //  Role.ROLE_FSK.getValue()
                                //  )

                                .requestMatchers(PathRequest.toH2Console()).hasRole("USER")
                                .anyRequest().authenticated())


                .httpBasic(Customizer.withDefaults())
        .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));


        return http.build();

    }


}
