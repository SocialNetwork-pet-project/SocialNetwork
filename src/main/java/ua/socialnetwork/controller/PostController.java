package ua.socialnetwork.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.socialnetwork.entity.Post;
import ua.socialnetwork.entity.User;
import ua.socialnetwork.service.PostService;
import ua.socialnetwork.service.UserService;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping({"/posts", "/"})
@AllArgsConstructor
@Slf4j
public class PostController {

    private UserService userService;
    private PostService postService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("posts", postService.getAll());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("userPrincipal", currentPrincipalName);



        return "feed";
    }
    @GetMapping("/s")
    public String getAllTwo(Model model){
        model.addAttribute("posts", postService.getAll());
        model.addAttribute("newPost", new Post());



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("userPrincipal", currentPrincipalName);


        return "feedTwo";
    }

//    @GetMapping("/new/{username}")
//    public String create(@PathVariable("username") String username, Model model){
//        User user = userService.readByUsername(username);
//        model.addAttribute("post", new Post());
//        model.addAttribute("owner", user);
//
//        return "create-post";
//    }

//    @PostMapping("/new/{username}")
//    public String create(@PathVariable("username") String username, @ModelAttribute("post") Post post, BindingResult result){
//
//        post.setUser(userService.readByUsername(username));
//        postService.create(post);
//        log.info("From PostController");
//        return "redirect:/posts";
//    }

    @PostMapping("/new/{username}")
    public String createTwo(@PathVariable("username") String username, @ModelAttribute("post") Post post, BindingResult result){

        post.setUser(userService.readByUsername(username));
        postService.create(post);
        log.info("From PostController");
        return "redirect:/s";
    }


















    @GetMapping("/principal")
    public String getPrincipal(@CurrentSecurityContext(expression = "authentication.principal")
                               Principal principal) {
        return principal.getName();
    }



}
