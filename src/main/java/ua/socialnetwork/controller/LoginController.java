package ua.socialnetwork.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.socialnetwork.service.UserService;

@Controller
public class LoginController {



    @GetMapping("/login")
    public String two() {

        return "login";
    }
}
