package com.mcflythekid.lazylearncore.config.jwt;

import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.entity.UserAuthority;
import com.mcflythekid.lazylearncore.repo.UserAuthorityRepo;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author McFly the Kid
 */
@Component
public class JWTTokenProvider {

    private final Logger log = LoggerFactory.getLogger(JWTTokenProvider.class);
    private static final String KEY_AUTHORITIES = "authorities";
    private static final String KEY_JTV = "jtv";

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Autowired
    private UserAuthorityRepo userAuthorityRepo;
    @Autowired
    private UserRepo userRepo;

    public String createToken(com.mcflythekid.lazylearncore.entity.User user) {
        String authorities = userAuthorityRepo.findAllByKeyUserId(user.getId())
                .stream().map(UserAuthority::getAuthority).collect(Collectors.joining(","));
        return Jwts.builder()
            .setSubject(user.getId())
            .claim(KEY_AUTHORITIES, authorities)
            .claim(KEY_JTV, user.getJtv())
            .setId(UUID.randomUUID().toString())
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .setExpiration(DateUtils.addSeconds(new Date(), Consts.PARAM_JWT_SECONDS))
            .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        String s = claims.get(KEY_AUTHORITIES) != null ? claims.get(KEY_AUTHORITIES).toString() : "";
        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(s.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String jwtToken) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody(); // Try parse

            String userId = claims.getSubject();
            String jtv = (String) claims.get(KEY_JTV);
            com.mcflythekid.lazylearncore.entity.User user = userRepo.findOne(userId);
            if (!user.getJtv().equals(jtv)){
                log.info("Invalid JWT jwv");
                return false;
            }

            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        } catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return false;
    }
}
