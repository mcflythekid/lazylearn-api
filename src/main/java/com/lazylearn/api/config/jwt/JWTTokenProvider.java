package com.mcflythekid.lazylearncore.config.jwt;

import com.mcflythekid.lazylearncore.config.Consts;
import com.mcflythekid.lazylearncore.config.exception.ExpiredJwtVersionException;
import com.mcflythekid.lazylearncore.entity.Session;
import com.mcflythekid.lazylearncore.entity.UserAuthority;
import com.mcflythekid.lazylearncore.indto.ClientData;
import com.mcflythekid.lazylearncore.repo.SessionRepo;
import com.mcflythekid.lazylearncore.repo.UserAuthorityRepo;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import com.mcflythekid.lazylearncore.service.AuthorityService;
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
    private static final String KEY__AUTHORITIES = "authorities";
    private static final String KEY__ACCESS_TOKEN_VERSION = "accessTokenVersion";
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SessionRepo sessionRepo;

    public String createToken(com.mcflythekid.lazylearncore.entity.User user, ClientData clientData) {
        Session session = new Session();
        session.setUserId(user.getId());
        session.setClientData(clientData.getData());
        sessionRepo.save(session);

        return Jwts.builder()
            .setSubject(user.getId())
            .setExpiration(DateUtils.addSeconds(new Date(), Consts.PARAM_JWT_SECONDS))
            .claim(KEY__AUTHORITIES, authorityService.getUserAuthorities(user.getId()))
            .claim(KEY__ACCESS_TOKEN_VERSION, user.getSessionKey())
            .claim("email", user.getEmail())
            .claim("fullName", user.getFullName())
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        String authoritiesString = claims.get(KEY__AUTHORITIES) != null ? claims.get(KEY__AUTHORITIES).toString() : "";

        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(authoritiesString.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String jwtToken) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody();
            com.mcflythekid.lazylearncore.entity.User user = userRepo.findOne(claims.getSubject());
            String accessTokenVersion = (String) claims.get(KEY__ACCESS_TOKEN_VERSION);
            if (!user.getSessionKey().equals(accessTokenVersion)) throw new ExpiredJwtVersionException();
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.", e);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.", e);
        } catch (ExpiredJwtVersionException e) {
            log.info("Expired JWT token. /by version");
        } catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return false;
    }
}
