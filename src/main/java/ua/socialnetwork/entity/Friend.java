package ua.socialnetwork.entity;


import jakarta.persistence.TableGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
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




    /*ToDo PROBABLY make another field -> boolean accepted = false, when receiver accept friend request, this
    variables goes true and the status changes to accepted, whatever its called
    */

}
