package ua.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "bio")
    private String bio;

    @Column(name = "sex")
    private String sex;

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
    //TODO figure out what column to write here and in Post class, user field
    //@Column(name = ??? )
    private List<Post> posts;

    @OneToOne(mappedBy = "user")
    private Account account;



}
