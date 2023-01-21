package ua.socialnetwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.socialnetwork.entity.Comment;
import ua.socialnetwork.repo.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl {

    @Autowired
    private CommentRepository commentRepository;
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(int post_id) {
        return commentRepository.findAllByPostId(post_id);
    }

}
