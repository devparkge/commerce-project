package github.hyungi.web.users.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserViewController {

    @GetMapping("/login")
    public String login() {
        return "users/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "users/signUp";
    }
}
