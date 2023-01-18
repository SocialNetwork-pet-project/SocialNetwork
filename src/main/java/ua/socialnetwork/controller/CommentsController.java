package ua.socialnetwork.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.socialnetwork.Dto.CommentDto;
import ua.socialnetwork.entity.Comment;
import ua.socialnetwork.entity.Post;
import ua.socialnetwork.service.impl.CommentServiceImpl;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentsController {

    private CommentServiceImpl commentService;


    @GetMapping("/add")
    public String createComment(Model model){
        model.addAttribute("comment", new Comment());
        return "create-comment";
    }

//    @PostMapping("/add")
//    public String create(@ModelAttribute("post") Comment comment){
//        commentService.create(comment);
//        return "redirect:/posts";
//    }
//
//    @PostMapping("/add")
//    public String createComment(@RequestBody CommentDto commentDto){
//        commentService.save(commentDto);
//        return "redirect:/posts";
//    }
}
