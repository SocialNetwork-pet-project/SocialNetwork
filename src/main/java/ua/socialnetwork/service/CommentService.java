package ua.socialnetwork.service;

import ua.socialnetwork.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment create(Comment comment);

    Comment update(Comment comment);

    void delete(Comment comment);

    Comment likeComment(Comment comment);

    Comment dislikeComment(Comment comment);

    Comment readById(Integer id);

    List<Comment> getAll();

    List<Comment> getCommentsByUserId(int userId);
}
