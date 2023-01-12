package ua.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.socialnetwork.entity.enums.FriendStatus;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "friend")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private int id;

    @Enumerated
    @Column(name = "status")
    private FriendStatus status;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "editionDate")
    private LocalDateTime editionDate;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private User sender;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private User receiver;






    /*ToDo PROBABLY make another field -> boolean accepted = false, when receiver accept friend request, this
    variables goes true and the status changes to accepted, whatever its called
    */


}
