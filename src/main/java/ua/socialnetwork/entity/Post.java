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
    private String creationDate;

    @Column(name = "editionDate")
    private String editionDate;

    @Column(name = "deletionDate")
    private String deletionDate;

    //here a multiple posts has 1 user, so @ManyToOne
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;   //owner ?? TODO
    public int getId() {
        return id;
    }

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
    private PostImage image;


    public void setImageToPost(PostImage userImage){

        userImage.setPost(this);
        image = userImage; //?
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", liked=" + liked +
                ", disliked=" + disliked +
                ", creationDate=" + creationDate +
                ", editionDate=" + editionDate +
                ", deletionDate=" + deletionDate +
                ", comments=" + comments +
                '}';
    }







}
