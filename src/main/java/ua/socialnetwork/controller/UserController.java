package ua.socialnetwork.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.socialnetwork.entity.Post;
import ua.socialnetwork.entity.User;
import ua.socialnetwork.exception.UserAlreadyExistsException;
import ua.socialnetwork.security.SecurityUser;
import ua.socialnetwork.service.PostService;
import ua.socialnetwork.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {


    UserService userService;
    PostService postService;

    @GetMapping("/create")
     public String create(Model model){

        model.addAttribute("user", new User());

        return "create-user";
    }

    @PostMapping("/create")
    public String create(@Validated  @ModelAttribute("user") User user, BindingResult result, Model model){
        //ToDo add actions with BindingResult later
        if(result.hasErrors()){
            return "create-user";

        }
        try{
            userService.create(user);

        }catch (UserAlreadyExistsException ex){
            log.warn("UserAlreadyExistsException is caught in UserController with mesasge: " + ex.getMessage() + " and" +
                            "cause " + ex.getCause()) ;
            model.addAttribute("message", "An account for that username/email already exists.");
            return "login-page";
        }

        return "redirect:/users/create/continue/" + user.getId();
    }

    @GetMapping("/update/{user_id}")
    public String updateForm(@PathVariable("user_id") Integer user_id,  Model model){
        User user = userService.readById(user_id);

        model.addAttribute("user", user);

        return "update-user";
    }
    @PostMapping("/update")
    public String update(@Validated User user, @RequestParam(value = "userImage", required = false) MultipartFile userImage,
                                                                                            BindingResult result){

        if(result.hasErrors()){
            log.warn("Binding result had an error in User Controller update with user: " +user.getUsername());
            return "update-user";

        }

        userService.update(user, userImage);
        user.setEditionDate(LocalDateTime.now());
        log.info("User with id: " + user.getId() + " has been updated");
        return "redirect:/users/"+user.getUsername();
    }


    @GetMapping("/create/continue/{user_id}")
    public String createSecondaryInfoForm(@PathVariable("user_id") Integer user_id,   Model model){
        User user = userService.readById(user_id);

        model.addAttribute("user", user);

        return "create-secondaryInfo";
    }

    @PostMapping("/create/continue/{user_id}")
    public String createSecondaryInfo(@PathVariable("user_id") Integer id,
                                      @Validated User user, @RequestParam(value = "userImage", required = false)MultipartFile userImage,
                                      @RequestParam(value = "imageBackground", required = false)MultipartFile imageBackground,
                                      BindingResult result){

        if(result.hasErrors()){

            return "create-secondaryInfo";
        }

        User oldUser = userService.readById(id);

        user.setFirstName(oldUser.getFirstName());
        user.setLastName(oldUser.getLastName());
        user.setUsername(oldUser.getUsername());
        user.setEmail(oldUser.getEmail());


        if(imageBackground.getSize() == 0 || imageBackground.getOriginalFilename() == null || imageBackground.isEmpty()){
            userService.update(user, userImage);
            return "redirect:/login";
        }


        userService.update(user, userImage, imageBackground);


        return "redirect:/login";
    }
    @GetMapping("/search")
    public String getAll(Model model){
        model.addAttribute("users", userService.getAll());
        return "Searchbar";
    }


    /// toDo give some errors


    @GetMapping("/{username}")
    public String getUser(@PathVariable("username") String username, Model model){
        User user = userService.readByUsername(username);
        List<Post> posts = postService.getPostsByUser_Username(username);

        boolean ifImageIsPresent = false;


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser u = (SecurityUser) authentication.getPrincipal();

        if(u.getImages().size() > 0){
            ifImageIsPresent = true;
        }


        model.addAttribute("imageIsPresent", ifImageIsPresent);
        model.addAttribute("posts", posts);

        model.addAttribute("user", user);
        model.addAttribute("image", user.getImages());


        model.addAttribute("size", user.getImages().size());
        return "profile-page";
    }


}
