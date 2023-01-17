package ua.socialnetwork.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.socialnetwork.entity.UserImage;

public interface UserImageRepo extends JpaRepository<UserImage, Integer > {
}
