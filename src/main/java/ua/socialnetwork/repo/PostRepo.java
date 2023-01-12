package ua.socialnetwork.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.socialnetwork.entity.Post;

import java.util.List;


public interface PostRepo extends JpaRepository<Post, Integer> {


    List<Post> getPostsByUserId(Integer id);

}
