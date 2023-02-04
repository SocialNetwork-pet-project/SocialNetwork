package ua.socialnetwork.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.socialnetwork.entity.Post;
import ua.socialnetwork.entity.PostImage;
import ua.socialnetwork.entity.User;
import ua.socialnetwork.entity.UserImage;
import ua.socialnetwork.entity.enums.PostAction;
import ua.socialnetwork.repo.PostRepo;
import ua.socialnetwork.service.PostService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepo postRepo;

    @Override
    public Post create(Post post) {
        //TODO make validations and exc handler
//        log.info("A post " + post.toString() + " was created in PostServiceImpl");

        post.setCreationDate(LocalDateTime.now());
        return postRepo.save(post);
    }
    @Override
    public Post create(Post post, MultipartFile postImage) {
        PostImage image;

        if (postImage.getSize() != 0) {
            image = toImageEntity(postImage);
            post.setImageToPost(image);
        }

        //TODO make validations and exc handler
        log.info("A post " + post.toString() + " was created in PostServiceImpl");

        post.setCreationDate(LocalDateTime.now());
        return postRepo.save(post);
    }

    @Override
    public Post update(Post post) {
        return null;
    }

    @Override
    public Post update(Post post, MultipartFile postImage) {
        PostImage image;

        if (postImage.getSize() != 0) {
            image = toImageEntity(postImage);
            post.setImageToPost(image);
        }



        post.setEditionDate(LocalDateTime.now());
        return postRepo.save(post);
    }


    @Override
    public Post readById(int id) {
        log.info("A post with id: " + id + " was read in PostServiceImpl");
        return postRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Post with id: " + id + "has not been found"));
    }
    @Override
    public void delete(int id) {

        postRepo.delete(readById(id));
    }

    @Override
    public List<Post> getAll() {
        //ToDO add validation and stuff later


        return postRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public List<Post> getByUserId(int userId) {
        return null;
    }

    @Override
    public List<Post> getPostsByUser_Username(String username){
        List<Post> posts = postRepo.getPostsByUser_Username(username, Sort.by(Sort.Direction.DESC, "id"));
        return posts;

    }

    @SneakyThrows
    private PostImage toImageEntity(MultipartFile postImage) {
        PostImage image = new PostImage();
        image.setName(postImage.getName());
        image.setOriginalFileName(postImage.getOriginalFilename());
        image.setContentType(postImage.getContentType());
        image.setSize(postImage.getSize());
        image.setBytes(postImage.getBytes());
        return image;
    }
    public void makeReaction(Post post, PostAction action){
        int likeCounter = post.getLikeCounter();
        int dislikeCounter = post.getDislikeCounter();

        switch (action) {
            case LIKE -> {
                post.setLiked(true);
                likeCounter++;
                post.setLikeCounter(likeCounter);
                if (dislikeCounter != 0) {
                    dislikeCounter--;

                }
                post.setDislikeCounter(dislikeCounter);
                post.setDisliked(false);
                log.info("Post with id: " + post.getId() + " is liked");
            }
            case DISLIKE -> {
                post.setDisliked(true);
                dislikeCounter++;
                post.setDislikeCounter(dislikeCounter);
                if (likeCounter != 0) {
                    likeCounter--;

                }
                post.setLikeCounter(likeCounter);
                post.setLiked(false);
                log.info("Post with id: " + post.getId() + " is disliked");
            }
        }

    }
}
