package ua.socialnetwork.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ua.socialnetwork.entity.User;
import ua.socialnetwork.repo.UserRepo;
import ua.socialnetwork.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepo userRepo;

    //ToDO implement validation and exception handler later

    @Override
    public User create(User user) {


        return userRepo.save(user);

    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User delete(int id) {
        return null;
    }

    @Override
    public User readById(int id) {

        return userRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with id: " + id + "not found"));
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
