package ua.socialnetwork.controller;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.socialnetwork.entity.Post;
import ua.socialnetwork.service.PostService;

import java.time.LocalDateTime;

@Controller
@RequestMapping({"/posts", "/"})
@AllArgsConstructor
@Slf4j
public class PostController {

    private PostService postService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("posts", postService.getAll());

        return "feed";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("post", new Post());

        return "create-post";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("post") Post post, BindingResult result){
        postService.create(post);
        log.info("From PostController set creationDate");
        return "redirect:/posts";
    }



}
