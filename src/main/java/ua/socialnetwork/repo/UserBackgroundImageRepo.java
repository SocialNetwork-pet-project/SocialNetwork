package ua.socialnetwork.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.socialnetwork.entity.UserBackgroundImage;

public interface UserBackgroundImageRepo extends JpaRepository<UserBackgroundImage, Integer> {
}
