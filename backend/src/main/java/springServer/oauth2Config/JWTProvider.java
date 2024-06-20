package springServer.oauth2Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JWTProvider {

    @Value("${jwt.secret_key}")
    private String secretKey;
    private final long expirationTime = 3600000; // 1h

    public String createToken(OAuth2User oAuth2User) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        com.auth0.jwt.JWTCreator.Builder tokenBuilder = JWT.create()
                .withSubject(oAuth2User.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .withIssuedAt(new Date());

        // Dodajemy atrybuty użytkownika jako claims
        for (Map.Entry<String, Object> entry : oAuth2User.getAttributes().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Boolean) {
                tokenBuilder.withClaim(key, (Boolean) value);
            } else if (value instanceof Integer) {
                tokenBuilder.withClaim(key, (Integer) value);
            } else if (value instanceof Long) {
                tokenBuilder.withClaim(key, (Long) value);
            } else if (value instanceof Double) {
                tokenBuilder.withClaim(key, (Double) value);
            } else if (value instanceof String) {
                tokenBuilder.withClaim(key, (String) value);
            } else if (value instanceof Date) {
                tokenBuilder.withClaim(key, ((Date) value).getTime());
            }
        }

        String sign = tokenBuilder.sign(algorithm);

        // Weryfikacja tokena i wypisanie dla debugowania
        DecodedJWT decodedJWT = verifyToken(sign);
        System.out.println("Secret key: " + secretKey);

        return sign;
    }

    public DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.require(algorithm).build().verify(token);
    }
}
