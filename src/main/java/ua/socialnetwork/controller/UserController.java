package ua.socialnetwork.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.socialnetwork.entity.User;
import ua.socialnetwork.service.UserService;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("/create")
    public String create(Model model){

        model.addAttribute("user", new User());


        return "create-user";
    }

    @PostMapping("/crehhate")
    public String create(@ModelAttribute("user") User user, Model model, BindingResult result){
        userService.create(user);
        return "redirect:/posts";
    }
}
