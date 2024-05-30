package SpringServer.oauth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import java.security.Principal;


@RestController
public class SessionController {

    @PreAuthorize("hasAuthority('SCOPE_mod_custom')")
    @GetMapping("/check-session")
    public String checkSession(Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof OAuth2User) {
            return "User is logged in: " + principal.toString();
        } else {
            return "User is not logged in";
        }
    }
}

