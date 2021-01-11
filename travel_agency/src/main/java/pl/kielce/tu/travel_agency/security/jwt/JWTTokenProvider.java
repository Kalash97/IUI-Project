package pl.kielce.tu.travel_agency.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import pl.kielce.tu.travel_agency.config.ServerConfig;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Component
public class JWTTokenProvider implements InitializingBean {

    private final Logger log = Logger.getLogger(JWTTokenProvider.class.getName());

    private static final String AUTHORITIES_KEY = "auth";

    private Key key;

    private long tokenValidityInMilliseconds;

    private long tokenValidityInMillisecondsForRememberMe;

    private KeyGenerator keyGenerator;

    private ServerConfig serverConfig;

    @Autowired
    public JWTTokenProvider(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(serverConfig.getSecret_key() == null) {
            this.keyGenerator = KeyGenerator.getInstance("HmacSHA512");
            this.key = keyGenerator.generateKey();
            log.info("Generated HmacSHA512 key is "+Base64.getEncoder().encodeToString(key.getEncoded()));
        }
        else {
            byte[] decodedKey = Base64.getDecoder().decode(serverConfig.getSecret_key());
            this.key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA512");
            log.info("Using key provided in properties.");
        }
        this.tokenValidityInMilliseconds = 1000 * 3600;
        this.tokenValidityInMillisecondsForRememberMe = 1000 * 3600 * 144;
    }

    public String createToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication
                .getAuthorities()
                .stream()
                //.peek(x -> log.info(((GrantedAuthority) x).getAuthority()))
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token.");
            log.info("Invalid JWT token trace.");
        }
        return false;
    }
}
