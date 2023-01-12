package ua.socialnetwork.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import ua.socialnetwork.entity.enums.AccountStatus;

@Entity
@Data
@NoArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accouunt_id")
    private int id;

    @Column(name = "banCount")
    private short banCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AccountStatus status;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;






}
