package ua.socialnetwork.service;

import ua.socialnetwork.entity.Post;

import java.util.List;

public interface PostService {
    Post create(Post post);

    Post update(Post post);

    void delete(int id);

    Post readById(int id);

    List<Post> getAll();

    List<Post> getByUserId(int userId);


}
