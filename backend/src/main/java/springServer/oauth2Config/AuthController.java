package springServer.oauth2Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;


@RestController
public class AuthController {
    @Value("${frontend.url}")
    private String frontendUrl;

    private final JWTProvider jwtTokenProvider;

    public AuthController(JWTProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

//    TODO konstruktor usera z obiektu oauth2user
//    @GetMapping("/loginSuccess")
//    public String loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User) {
//
//        String token = jwtTokenProvider.createToken(oAuth2User);
//        return "Wygenerowany token:\n" + token;
//    }

    @GetMapping("/loginSuccess")
    public ResponseEntity<Void> loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User) throws URISyntaxException {
        String token = jwtTokenProvider.createToken(oAuth2User);
        Cookie jwtCookie = new Cookie("token", token);

        // Przekierowanie
        URI redirectUri = new URI(frontendUrl + "/profile/" + token);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(redirectUri);

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}

