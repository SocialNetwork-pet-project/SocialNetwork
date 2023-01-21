package ua.socialnetwork.service;

import ua.socialnetwork.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment create(Comment comment);

    Comment update(Comment comment);

    void delete(Long id);

    Comment likeComment(Comment comment);

    Comment dislikeComment(Comment comment);

    Comment readById(Long id);

    List<Comment> getCommentsByPostId(int postId);

    List<Comment> getAll();

    List<Comment> getCommentsByUserId(int userId);
}
