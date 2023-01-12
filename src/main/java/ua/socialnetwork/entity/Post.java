package ua.socialnetwork.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "liked")
    private boolean liked;

    @Column(name = "disliked")
    private boolean disliked;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "editionDate")
    private LocalDateTime editionDate;

    @Column(name = "deletionDate")
    private LocalDateTime deletionDate;

    //here a multiple posts has 1 user, so @ManyToOne
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;   //owner ?? TODO

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creationDate=" + creationDate +
                ", user=" + user +
                '}';
    }
}
