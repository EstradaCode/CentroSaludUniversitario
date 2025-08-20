package Utils.Session;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.*;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.*;
import java.util.*;

@ApplicationScoped
public class JwtService {
    private Algorithm alg;
    private JWTVerifier verifier;
    private String issuer;
    private long accessMinutes;

    @PostConstruct void init() {
        String secret = Optional.ofNullable(System.getenv("JWT_SECRET"))
                .orElseThrow(() -> new IllegalStateException("JWT_SECRET requerido"));
        issuer = Optional.ofNullable(System.getenv("JWT_ISSUER")).orElse("salud-api");
        accessMinutes = Optional.ofNullable(System.getenv("JWT_ACCESS_MINUTES"))
                .map(Long::parseLong).orElse(10L);
        alg = Algorithm.HMAC256(secret);
        verifier = JWT.require(alg).withIssuer(issuer).build();
    }

    public String emitirAccess(Long userId, String username, List<String> roles) {
        Instant now = Instant.now();
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(String.valueOf(userId))
                .withClaim("usr", username)
                .withArrayClaim("roles", roles.toArray(new String[0]))
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(Duration.ofMinutes(accessMinutes))))
                .sign(alg);
    }

    public DecodedJWT verificar(String token) { return verifier.verify(token); }
}

