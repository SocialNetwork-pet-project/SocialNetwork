package ua.socialnetwork.controller;


import com.google.common.io.ByteSource;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.socialnetwork.entity.UserImage;
import ua.socialnetwork.repo.UserImageRepo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final UserImageRepo userImageRepo;

    @SneakyThrows
    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImage(@PathVariable Integer id){
        //TODO Add exception handler here
        UserImage image = userImageRepo.findById(id).orElse(null);

        InputStream s = ByteSource.wrap(image.getBytes()).openStream();

        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
//                .body(s);
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));





    }




}
