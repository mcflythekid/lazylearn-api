package com.mcflythekid.lazylearncore.config;

import com.mcflythekid.lazylearncore.config.jwt.JWTSecurityConfigurer;
import com.mcflythekid.lazylearncore.config.jwt.JWTTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

/**
 * Created by nydiarra on 06/05/17.
 */
@Configuration
@Import(SecurityProblemSupport.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTTokenProvider jwtTokenProvider;
    private final SecurityProblemSupport problemSupport;
    private JWTSecurityConfigurer jwtSecurityConfigurer() {
        return new JWTSecurityConfigurer(jwtTokenProvider);
    }

    public SecurityConfig(JWTTokenProvider jwtTokenProvider, SecurityProblemSupport problemSupport) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.problemSupport = problemSupport;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .logout()
                .disable()
            .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport)
            .and()
                .headers()
                .frameOptions()
                .disable()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/forget-password", "/reset-password", "/register", "/login", "/login-facebook").permitAll()
                .antMatchers("/admin/**").hasAuthority(Consts.AUTHORITY_ADMIN)
                .anyRequest().hasAuthority(Consts.AUTHORITY_DEFAULT)
            .and()
                .apply(jwtSecurityConfigurer());
    }
}