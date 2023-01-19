package ua.socialnetwork.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Data
@NoArgsConstructor
@Table(name = "images")
public class UserImage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "originalFileName")
    private String originalFileName;

    @Column(name = "size")
    private Long size;


    @Column(name = "contentType")
    private String contentType;

    @Lob
    private byte[] bytes;

//    @OneToOne(cascade = CascadeType.ALL) //cascade = CascadeType.ALL
//    @JoinColumn(name = "user_id")
//    private User user;



    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;





}
