package ua.socialnetwork.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.socialnetwork.entity.Comment;
import ua.socialnetwork.repo.CommentRepository;
import ua.socialnetwork.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }
    @Override
    public Comment update(Comment comment) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public Comment likeComment(Comment comment) {
        return null;
    }

    @Override
    public Comment dislikeComment(Comment comment) {
        return null;
    }

    @Override
    public Comment readById(Long id) {
        return null;
    }

    public List<Comment> getCommentsByPostId(int post_id) {
        return commentRepository.findAllByPostId(post_id);
    }

    @Override
    public List<Comment> getAll() {
        return null;
    }

    @Override
    public List<Comment> getCommentsByUserId(int userId) {
        return null;
    }

}
