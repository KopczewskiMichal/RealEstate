package springServer.oauth2Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

@Service
public class JWTProvider {

    @Value("${jwt.secret_key}")
    private String secretKey;
    private final long expirationTime = 3600000; // 1h

    // Metoda do generowania tokenu z OAuth2User
    public String createToken(OAuth2User oAuth2User) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Map<String, Object> claims = new HashMap<>();

        oAuth2User.getAttributes().forEach((key, value) -> {
            if (value instanceof Boolean ||
                    value instanceof Integer ||
                    value instanceof Long ||
                    value instanceof Double ||
                    value instanceof String ||
                    value instanceof Date) {
                claims.put(key, value);
            }
        });

        return JWT.create()
                .withSubject(oAuth2User.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .withIssuedAt(new Date())
                .withPayload(claims)
                .sign(algorithm);
    }

    // Statyczna metoda do testów
    public String createToken() {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withSubject("staticUser")
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .withIssuedAt(new Date())
                .withClaim("role", "USER")
                .sign(algorithm);
    }

    public DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.require(algorithm).build().verify(token);
    }
}
