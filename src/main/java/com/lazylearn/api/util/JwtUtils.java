package com.lazylearn.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.impl.DefaultJwtParser;

/**
 * @author McFly the Kid
 */
public final class JwtUtils {
    private JwtUtils() {
    }

    /**
     * Parse without secret
     *
     * @param jwtToken
     * @return
     */
    public static Claims parse(String jwtToken) {
        String[] splitToken = jwtToken.split("\\.");
        String unsignedToken = splitToken[0] + "." + splitToken[1] + ".";

        DefaultJwtParser parser = new DefaultJwtParser();
        Jwt<?, ?> jwt = parser.parse(unsignedToken);
        Claims claims = (Claims) jwt.getBody();
        return claims;
    }

    public static String getSignature(String jwtToken) {
        return jwtToken.split("\\.")[2];
    }
}