package com.mcflythekid.lazylearncore.config.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @author McFly the Kid
 */
public class JWTFilter extends GenericFilterBean {

    private final Logger log = LoggerFactory.getLogger(JWTFilter.class);
    private JWTTokenProvider jwtTokenProvider;

    public JWTFilter(JWTTokenProvider JWTTokenProvider) {
        this.jwtTokenProvider = JWTTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String accessToken = resolveToken((HttpServletRequest) servletRequest);
        if (StringUtils.hasText(accessToken) && jwtTokenProvider.validateToken(accessToken)) {
            SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(accessToken));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(HttpServletRequest request){
        try{
            String bearerToken = request.getHeader("Authorization");
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7, bearerToken.length());
            }
        } catch (Exception e){
            log.error("Cannot get access token from header", e);
        }
        return null;
    }
}