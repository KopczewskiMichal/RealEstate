package springServer.testEndpoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@Deprecated
@RestController
public class SimpleEndpoints {

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/")
    public String home() {
        return "Hello, Home!";
    }

    @GetMapping("/secured")
    public String secured() {
        return "Hello, Secured!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin() { return "Super, I'm Admin!!!"; }
}
