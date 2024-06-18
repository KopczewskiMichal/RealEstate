package springServer.oauth2Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final JWTProvider jwtProvider;

    @Autowired
    public AuthController(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess() {
        OAuth2User oAuth2User = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = jwtProvider.createToken(oAuth2User);
        return "Bearer " + token;
    }

    @GetMapping("/generateStaticToken")
    public String generateStaticToken() {
        String token = jwtProvider.createToken();
        return "Bearer " + token;
    }
}
