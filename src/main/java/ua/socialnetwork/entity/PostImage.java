package ua.socialnetwork.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

@Entity
@Data
@NoArgsConstructor
@Table(name = "postImages")
@ToString
@EqualsAndHashCode
public class PostImage {


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

    @OneToOne(mappedBy = "image",cascade = CascadeType.ALL) //cascade = CascadeType.ALL
    @JoinColumn(name = "post_id")
    private Post post;








}
