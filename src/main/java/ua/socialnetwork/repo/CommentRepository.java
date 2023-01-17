package ua.socialnetwork.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.socialnetwork.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    String findByPostId(Integer id);  //need check
}
