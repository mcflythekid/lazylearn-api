package com.lazylearn.api.config;

import com.lazylearn.api.config.jwt.JWTSecurityConfigurer;
import com.lazylearn.api.config.jwt.JWTTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by nydiarra on 06/05/17.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTTokenProvider jwtTokenProvider;
    private JWTSecurityConfigurer jwtSecurityConfigurer() {
        return new JWTSecurityConfigurer(jwtTokenProvider);
    }

    public SecurityConfig(JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .logout().disable().csrf().disable().headers().frameOptions().disable()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/forget-password", "/reset-password", "/register", "/login", "/login-facebook").permitAll()
                .antMatchers("/admin/**").hasAuthority(Consts.AUTHORITY_ADMIN)

                .antMatchers("/minpair/admin/**").hasAuthority(Consts.AUTHORITY_ADMIN)


                .antMatchers("/force-login/*").hasAuthority(Consts.AUTHORITY_ADMIN)

                .antMatchers("/article/admin/**").hasAuthority(Consts.AUTHORITY_ADMIN)

                .antMatchers("/file/**").permitAll()
                .anyRequest().hasAuthority(Consts.AUTHORITY_DEFAULT)
            .and()
                .apply(jwtSecurityConfigurer());
    }
}