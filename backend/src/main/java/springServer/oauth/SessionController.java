package springServer.oauth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springServer.users.User;

@RestController
public class SessionController {

    @GetMapping("/check-session")
    public String checkSession () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof OAuth2User oauthUser) {
            String username = oauthUser.getAttribute("given_name");
            String email = oauthUser.getAttribute("email");
            String profilePictureUrl = oauthUser.getAttribute("picture");
            User actUser = new User(username, email, profilePictureUrl);

            return actUser.toString() + "\nhashCode:  " + actUser.hashCode();
        } else {
            return "User is not logged in";
        }
    }


}

