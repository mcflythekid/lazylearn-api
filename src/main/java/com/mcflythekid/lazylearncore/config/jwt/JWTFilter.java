package com.mcflythekid.lazylearncore.config.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is
 * found.
 */
public class JWTFilter extends GenericFilterBean {

    private JWTTokenProvider JWTTokenProvider;

    public JWTFilter(JWTTokenProvider JWTTokenProvider) {
        this.JWTTokenProvider = JWTTokenProvider;
    }

    private String resolveToken(HttpServletRequest request){
        final String TOKEN_PREFIX = "Bearer ";
        final String AUTHORIZATION_HEADER = "Authorization";
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        if (StringUtils.hasText(jwt) && JWTTokenProvider.validateToken(jwt)) {
            Authentication authentication = JWTTokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}