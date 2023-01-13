package ua.socialnetwork.service;

import ua.socialnetwork.entity.User;
import java.util.*;

public interface UserService {

    User create(User user);
    User update(User user);
    User delete(int id);
    User readById(int id);
    List<User> getAll();

}
