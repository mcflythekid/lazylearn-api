package com.lazylearn.api.config.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JWTTokenProvider JWTTokenProvider;

    public JWTSecurityConfigurer(JWTTokenProvider JWTTokenProvider) {
        this.JWTTokenProvider = JWTTokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) {
        JWTFilter customFilter = new JWTFilter(JWTTokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
