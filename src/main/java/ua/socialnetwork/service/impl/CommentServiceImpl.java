package ua.socialnetwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.socialnetwork.Dto.CommentDto;
import ua.socialnetwork.entity.Comment;
import ua.socialnetwork.entity.Post;
import ua.socialnetwork.repo.CommentRepository;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl {

    @Autowired
    private CommentRepository commentRepository;
    public Comment save(CommentDto commentDto) {

        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setCreatedDate(LocalDateTime.now());
        comment.setEditedDate(LocalDateTime.now());
        comment.getId();
        comment.getPost();

        return commentRepository.save(comment);
    }

//    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }
}
