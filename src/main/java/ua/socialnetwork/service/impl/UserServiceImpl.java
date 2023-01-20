package ua.socialnetwork.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DialectOverride;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.socialnetwork.entity.User;
import ua.socialnetwork.entity.UserBackgroundImage;
import ua.socialnetwork.entity.UserImage;
import ua.socialnetwork.exception.NullEntityReferenceException;
import ua.socialnetwork.repo.UserRepo;
import ua.socialnetwork.service.UserService;


import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private PasswordEncoder encoder;
    private UserRepo userRepo;

    //ToDO implement validation and exception handler later

//    @Override
//    public User create(User user ) {
//
//        user.setPassword(encoder.encode(user.getPassword()));
//        user.setCreationDate(LocalDateTime.now());
//        return userRepo.save(user);
//
//    }
//
//    @Override
//    public User create(User user, MultipartFile userImage) {
//        return null;
//    }

    @Override
    public User create(User user, MultipartFile userImage) {
        UserImage image;

        if (userImage.getSize() != 0) {
            image = toImageEntity(userImage);
            user.addImageToUser(image);
        }
        log.info("Added image: " + userImage.getName());

        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreationDate(LocalDateTime.now());
        return userRepo.save(user);

    }

    @Override
    public User create(User user, MultipartFile userImage, MultipartFile imageBackground) {

        UserImage image;
        UserImage image2;

        if (userImage.getSize() != 0) {
            image = toImageEntity(userImage);
            user.addImageToUser(image);
        }
        if (userImage.getSize() != 0) {
            image2 = toImageEntity(imageBackground);
            user.addImageToUser(image2);
        }

        log.info("Added image: " + userImage.getName());
        log.info("Added background image: " + imageBackground.getName());

        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreationDate(LocalDateTime.now());
        return userRepo.save(user);

    }

    @Override
    public User update(User user) {
        return null;
    }


    //ToDO make 1 method to not duplicate code

    @Override
    public User update(User user, MultipartFile userImage) {
        UserImage image;

        if (userImage.getSize() != 0) {
            image = toImageEntity(userImage);
            user.setImageToUser(image);
        }



        // user.getImages().set(0 , i);

        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreationDate(LocalDateTime.now());
        return userRepo.save(user);
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
    public User readByUsername(String username) {
        return userRepo.findUserByUsername(username).orElseThrow(() ->
                new EntityNotFoundException("User with id: " + username + "not found"));
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @SneakyThrows
    private UserImage toImageEntity(MultipartFile userImage) {
        UserImage image = new UserImage();
        image.setName(userImage.getName());
        image.setOriginalFileName(userImage.getOriginalFilename());
        image.setContentType(userImage.getContentType());
        image.setSize(userImage.getSize());
        image.setBytes(userImage.getBytes());
        return image;
    }
}
