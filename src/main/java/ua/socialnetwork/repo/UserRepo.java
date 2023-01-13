package ua.socialnetwork.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.socialnetwork.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {



}
