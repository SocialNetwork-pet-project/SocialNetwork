package ua.socialnetwork.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.socialnetwork.entity.Comment;
import ua.socialnetwork.repo.CommentRepository;
import ua.socialnetwork.service.PostService;
import ua.socialnetwork.service.impl.CommentServiceImpl;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/comment")
@Slf4j
public class CommentsController {

    private final CommentServiceImpl commentService;
    private final PostService postService;

    private CommentRepository commentRepository;

    @GetMapping("/add/{post_id}")
    public String createComment(@PathVariable("post_id") long post_id, Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("postId", post_id);
        return "create-comment";
    }

    @PostMapping("/add/{post_id}")
    public String create(@PathVariable("post_id") int post_id,
                         @Validated @ModelAttribute("comment") Comment comment) {
        try {
            comment.setPost(postService.readById(post_id));
            commentService.create(comment);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Exception");
        }
        log.info("Comment with id:" + comment.getId() + " was created");
        return "redirect:/posts";
    }

    @GetMapping("/liked/{comment_id}")
    public String like(@PathVariable("comment_id") long comment_id, Comment comment){
        comment= (Comment) commentRepository.findCommentById(comment_id);
        comment.setLiked(true);
        commentRepository.save(comment);
        return "redirect:/comment/all/" + comment.getPost().getId();
    }

    @GetMapping("/disliked/{comment_id}")
    public String disLike(@PathVariable("comment_id") long comment_id, Comment comment){
        comment = (Comment) commentRepository.findCommentById(comment_id);
        comment.setDisliked(true);
        commentRepository.save(comment);
        return "redirect:/comment/all/" + comment.getPost().getId();
    }


    @GetMapping("/all/{post_id}")
    public String getAll (@PathVariable("post_id") int post_id, Model model){
        List<Comment> allComments = commentService.getCommentsByPostId(post_id);
        model.addAttribute("allComments", allComments);
        model.addAttribute("post", postService.readById(post_id));

        log.info("Comments have been reading");
        return "post-comments";
    }
}
