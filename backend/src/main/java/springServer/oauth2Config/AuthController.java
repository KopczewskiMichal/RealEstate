package springServer.oauth2Config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springServer.users.User;

@RestController
public class AuthController {

    private final JWTProvider jwtTokenProvider;

    public AuthController(JWTProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

//    TODO konstruktor usera z obiektu oauth2user
    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User) {

        String token = jwtTokenProvider.createToken(oAuth2User);
        return "Wygenerowany token: " + token;
    }
}

