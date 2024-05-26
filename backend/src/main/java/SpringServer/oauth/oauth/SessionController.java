package SpringServer.oauth.oauth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/check-session")
    public String checkSession(@RequestParam String sessionId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof OAuth2User) {
            return "User is logged in";
        } else {
            return "User is not logged in";
        }
    }

//    @GetMapping("/login")
//    public ResponseEntity<String> login () {
//        return new ResponseEntity<String>(
//                "Siemasz, to jest strona logowania",
//                HttpStatus.OK
//        );
//    }
}

