package ua.socialnetwork.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.socialnetwork.entity.Post;
import ua.socialnetwork.entity.User;
import ua.socialnetwork.security.SecurityUser;
import ua.socialnetwork.service.PostService;
import ua.socialnetwork.service.UserService;


import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping({"/posts", "/"})
@Slf4j

public class PostController {
    private int likeCounter;
    private int dislikeCounter;


    private final UserService userService;
    private final PostService postService;





    public PostController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }



    //ToDO make validation and exc handling
    @GetMapping("/feed")

    public String getAllTwo(Model model){

        boolean ifImageIsPresent = false;

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        SecurityUser u = (SecurityUser) authentication.getPrincipal();

        if(u.getImages().size() > 0){
            ifImageIsPresent = true;
        }


        model.addAttribute("posts", postService.getAll());
        model.addAttribute("newPost", new Post());


        model.addAttribute("ifImageIsPresent", ifImageIsPresent);


        model.addAttribute("auth", authentication);


        return "feed";
    }

    @PostMapping("/new/{username}")
    public String createTwo(@PathVariable("username") String username, Post post,
                            @RequestParam(value = "postImage", required = false) MultipartFile postImage, BindingResult result){




        post.setUser(userService.readByUsername(username));
        postService.create(post, postImage);
        log.info("From PostController");
        return "redirect:/feed";
    }


    @GetMapping("/update/{post_id}")
    public String editForm(@PathVariable("post_id") Integer id, Model model){
        Post post = postService.readById(id);

        model.addAttribute("post", post);

        return "update-post";

    }

    @PostMapping("/update")
    public String edit(Post post, BindingResult result,
                       @RequestParam(value = "postImage", required = false) MultipartFile postImage ){




        postService.update(post, postImage);
        post.setEditionDate(LocalDateTime.now());



        return "redirect:/feed";
    }
    @GetMapping("/delete/{post_id}")
    public String delete(@PathVariable("post_id") Integer post_id){
        postService.delete(post_id);


        return "redirect:/feed";
    }




    @GetMapping("/like/{post_id}")
    public String like(@PathVariable("post_id") Integer post_id, Model model){

        Post post = postService.readById(post_id);
        post.setLiked(true);
        likeCounter++;
        if(dislikeCounter != 0){
            dislikeCounter--;

        }
        post.setLikeCounter(likeCounter);
        post.setDislikeCounter(dislikeCounter);
        post.setDisliked(false);

        postService.create(post);
        return "redirect:/feed";


    }
    @GetMapping("/dislike/{post_id}")
    public String dislike(@PathVariable("post_id") Integer post_id, Model model){
        Post post = postService.readById(post_id);
        post.setDisliked(true);
        dislikeCounter++;
        if(likeCounter != 0){
            likeCounter--;

        }
        post.setLikeCounter(likeCounter);
        post.setDislikeCounter(dislikeCounter);
        post.setLiked(false);

        postService.create(post);
        return "redirect:/feed";

    }
}
