package ua.socialnetwork.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.socialnetwork.entity.Post;
import ua.socialnetwork.repo.PostRepo;
import ua.socialnetwork.service.PostService;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepo postRepo;

    @Override
    public Post create(Post post) {
        //TODO make validations and exc handler
        log.info("A post " + post.toString() + " was created in PostServiceImpl");
        return postRepo.save(post);
    }

    @Override
    public Post update(Post post) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Post readById(int id) {
        log.info("A post with id: " + id + " was read in PostServiceImpl");
        return postRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Post with id: " + id + "has not been found"));
    }

    @Override
    public List<Post> getAll() {
        //ToDO add validation and stuff later
        return postRepo.findAll();
    }

    @Override
    public List<Post> getByUserId(int userId) {
        return null;
    }
}
