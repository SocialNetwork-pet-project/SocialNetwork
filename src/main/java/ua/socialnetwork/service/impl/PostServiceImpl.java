package ua.socialnetwork.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.socialnetwork.entity.Post;
import ua.socialnetwork.entity.PostImage;
import ua.socialnetwork.entity.UserImage;
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
        log.info("A post " + post.toString() + " was created in PostServiceImpl");
        ;
        post.setCreationDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
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
        ;
        post.setCreationDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
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
}
