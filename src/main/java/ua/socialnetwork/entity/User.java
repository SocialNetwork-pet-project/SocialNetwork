package ua.socialnetwork.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ua.socialnetwork.entity.enums.Gender;
import ua.socialnetwork.entity.enums.UserRole;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = ua.test.testtask.UserSerializer.class)
@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "firstName", nullable = false)

    private String firstName;

    @Column(name = "lastName", nullable = false)

    private String lastName;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "bio")
    private String bio;

    @Column(name = "country")
    private String country;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "employed")
    private boolean employed;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "banned")
    private boolean banned;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "editionDate")
    private LocalDateTime editionDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Friend> friends;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "user")
    private List<UserImage> images = new ArrayList<>();

    public void setProfileImageToUser(UserImage image) {
        image.setUser(this);
        images.add(image);
    }

    //Needed to set user`s BACKGROUND image (which can not be changed)
    public void setBackgroundImageToUser(UserImage image){
        image.setUser(this);
        if(this.getImages().size() >= 1){
            images.add(0, image);
        }
        images.add(image);
    }

}
