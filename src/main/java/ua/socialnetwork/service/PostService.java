package ua.socialnetwork.service;

import org.springframework.web.multipart.MultipartFile;
import ua.socialnetwork.entity.Post;
import ua.socialnetwork.entity.enums.PostAction;

import java.util.List;

public interface PostService {
    Post create(Post post);
    Post create(Post post, MultipartFile postImage);

    Post update(Post post);
    Post update(Post post, MultipartFile multipartFile);

    void delete(int id);

    Post readById(int id);
    public List<Post> getPostsByUser_Username(String username);

    List<Post> getAll();

    List<Post> getByUserId(int userId);
    void makeReaction(Post post, PostAction action);


}
