package ua.socialnetwork.controller;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.socialnetwork.entity.User;
import ua.socialnetwork.entity.UserImage;
import ua.socialnetwork.service.UserService;

import java.time.LocalDateTime;

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

//    @PostMapping("/create")
//    public String create(@ModelAttribute("user") User user){
//        //ToDo add actions with BindingResult later
//        userService.create(user);
//        return "redirect:/login";
//    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user, @RequestParam(value = "userImage", required = false)MultipartFile userImage,
                         @RequestParam(value = "imageBackground", required = false)MultipartFile imageBackground){
        //ToDo add actions with BindingResult later

        if(imageBackground.getSize() == 0 || imageBackground.getOriginalFilename() == null || imageBackground.isEmpty()){
            userService.update(user, userImage);
            return "redirect:/login";
        }
        userService.create(user, userImage, imageBackground);
        return "redirect:/login";
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













    @GetMapping("/{username}")
    public String getUser(@PathVariable("username") String username, Model model){
        User user = userService.readByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("image", user.getImages());

        model.addAttribute("size", user.getImages().size());
        return "profile-page";


    }


    @SneakyThrows
    private UserImage toImageEntity(MultipartFile userImage){
        UserImage image = new UserImage();
        image.setName(userImage.getName());
        image.setOriginalFileName(userImage.getOriginalFilename());
        image.setContentType(userImage.getContentType());
        image.setSize(userImage.getSize());
        image.setBytes(userImage.getBytes());
        return image;
    }



}
