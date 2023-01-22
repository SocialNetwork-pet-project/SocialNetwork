package ua.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ua.socialnetwork.entity.enums.Gender;
import ua.socialnetwork.entity.enums.UserRole;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@Entity

@EqualsAndHashCode
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
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

    //here we 1 user has multiple posts, so @OneToMany
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToOne(mappedBy = "user")
    private Account account;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "user")
    private List<UserImage> images = new ArrayList<>();



    public void addImageToUser(UserImage image) {
        image.setUser(this);
        images.add(image);
    }

    public void setImageToUser(UserImage image){
        image.setUser(this);
        if(this.getImages().size() >= 1){
            images.add(0, image);
        }
        images.add(image);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", bio='" + bio + '\'' +
                ", country='" + country + '\'' +
                ", gender=" + gender +
                ", employed=" + employed +
                ", role=" + role +
                ", banned=" + banned +
                ", creationDate=" + creationDate +
                ", editionDate=" + editionDate +
                ", posts=" + posts +
                ", account=" + account +
                '}';
    }
}
