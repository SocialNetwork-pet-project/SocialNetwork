package ua.socialnetwork.service;

import org.springframework.web.multipart.MultipartFile;
import ua.socialnetwork.entity.User;
import java.util.*;

public interface UserService {

    User create(User user, MultipartFile userImage);
    User update(User user);
    User delete(int id);
    User readById(int id);
    User readByUsername(String username);
    List<User> getAll();

}
