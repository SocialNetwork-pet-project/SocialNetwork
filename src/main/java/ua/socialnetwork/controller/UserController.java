package ua.socialnetwork.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user, @RequestParam("userImage")MultipartFile userImage){
        //ToDo add actions with BindingResult later
        userService.create(user, userImage);
        return "redirect:/login";
    }


    @GetMapping("/{username}")
    public String getUser(@PathVariable("username") String username, Model model){
        User user = userService.readByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("image", user.getImage());
        return "profile-page";


    }
}
