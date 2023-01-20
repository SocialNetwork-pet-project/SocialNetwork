package ua.socialnetwork.service;

import org.springframework.web.multipart.MultipartFile;
import ua.socialnetwork.entity.User;
import java.util.*;

public interface UserService {

    User create(User user);
    User create(User user, MultipartFile userImage);
    User create(User user, MultipartFile userImage, MultipartFile imageBackground);
    User update(User user);
    User update(User user, MultipartFile userImage);
    User update(User user, MultipartFile userImage, MultipartFile imageBackground);
    User delete(int id);
    User readById(int id);
    User readByUsername(String username);
    List<User> getAll();

}
