package ua.socialnetwork.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.socialnetwork.entity.Post;
import ua.socialnetwork.entity.User;
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
    SecurityUser su;


    @GetMapping("/create")
     public String create(Model model){

        model.addAttribute("user", new User());

        return "create-user";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user){
        //ToDo add actions with BindingResult later

        userService.create(user);
        return "redirect:/users/create/continue/" + user.getId();
    }

    @GetMapping("/update/{user_id}")
    public String updateForm(@PathVariable("user_id") Integer user_id,  Model model){
        User user = userService.readById(user_id);

        model.addAttribute("user", user);

        return "update-user";
    }
    @PostMapping("/update")
    public String update(User user, @RequestParam(value = "userImage", required = false) MultipartFile userImage){


        userService.update(user, userImage);
        user.setEditionDate(LocalDateTime.now());

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
                                      User user, @RequestParam(value = "userImage", required = false)MultipartFile userImage,
                                      @RequestParam(value = "imageBackground", required = false)MultipartFile imageBackground){


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





    @GetMapping("/{username}")
    public String getUser(@PathVariable("username") String username, Model model){
        int imgId = su.getImage();


        User user = userService.readByUsername(username);
        List<Post> posts = postService.getPostsByUser_Username(username);
        model.addAttribute("posts", posts);
        model.addAttribute("user", user);
        model.addAttribute("image", user.getImages());
        model.addAttribute("imgId",imgId);


        model.addAttribute("size", user.getImages().size());
        return "profile-page";


    }






}
