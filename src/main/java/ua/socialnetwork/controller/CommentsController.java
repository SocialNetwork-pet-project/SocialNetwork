package ua.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.socialnetwork.Dto.CommentDto;
import ua.socialnetwork.entity.Comment;
import ua.socialnetwork.service.impl.CommentServiceImpl;

@RestController
@RequestMapping("/comment")
public class CommentsController {

    @Autowired
    private CommentServiceImpl commentService;


    //haven`t idea how to make return on HTML page
    // improve like on screenshot
//    @PostMapping("")
//    public ResponseEntity<Comment> createComment(@RequestBody CommentDto commentDto) {
////        System.out.println(commentDto);
//        Comment comment = commentService.save(commentDto);
//        return ResponseEntity.ok(comment);
//    }




//    @PostMapping("/creating/comment")
//    public String createComment(@RequestBody)
}
