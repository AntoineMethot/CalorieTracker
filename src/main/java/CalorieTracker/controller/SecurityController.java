package CalorieTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "security/access-denied";
    }
}
