package ua.socialnetwork.repo;

import jakarta.persistence.OrderBy;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.socialnetwork.entity.Post;

import java.util.List;


public interface PostRepo extends JpaRepository<Post, Integer> {


    List<Post> getPostsByUserId(Integer id);
    List<Post> getPostsByUser_Username(String username, Sort sort);

//    @Override
//    @Query("SELECT p from Post p where ORDER BY id desc ")
//    List<Post> findAll();

}
