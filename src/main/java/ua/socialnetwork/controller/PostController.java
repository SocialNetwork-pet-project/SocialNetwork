package ua.socialnetwork.controller;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.socialnetwork.entity.Post;
import ua.socialnetwork.service.PostService;

@Controller
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("posts", postService.getAll());

        return "feed";
    }

    @PostMapping("/new")
    public String create(Model model){
        model.addAttribute("post", new Post());

        return "create-post";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("post") Post post, BindingResult result){
        Post newPost = postService.create(post);
        return "redirect:/posts";
    }



}
