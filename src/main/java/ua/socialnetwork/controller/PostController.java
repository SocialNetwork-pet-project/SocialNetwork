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
import org.springframework.web.multipart.MultipartFile;
import ua.socialnetwork.entity.Post;
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

    @GetMapping("/feed")
    public String getAllTwo(Model model){
        model.addAttribute("posts", postService.getAll());
        model.addAttribute("newPost", new Post());



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("userPrincipal", currentPrincipalName);


        return "feedTwo";
    }

    @PostMapping("/new/{username}")
    public String createTwo(@PathVariable("username") String username, Post post,
                            @RequestParam(value = "postImage", required = false) MultipartFile postImage, BindingResult result){




        post.setUser(userService.readByUsername(username));
        postService.create(post, postImage);
        log.info("From PostController");
        return "redirect:/feed";
    }


    @GetMapping("/edit/{post_id}")
    public String editForm(@PathVariable("post_id") Integer id, Model model){
        Post post = postService.readById(id);

        model.addAttribute("post", post);

        return "update-post";

    }

    @PostMapping("/edit")
    public String edit(Post post, BindingResult result,
                       @RequestParam(value = "postImage", required = false) MultipartFile postImage ){

        postService.update(post, postImage);
        post.setEditionDate(LocalDateTime.now());



        return "redirect:/feed";
    }
















    @GetMapping("/principal")
    public String getPrincipal(@CurrentSecurityContext(expression = "authentication.principal")
                               Principal principal) {
        return principal.getName();
    }



}
