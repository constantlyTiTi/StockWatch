package ting.li.accountsecurity.dao;

import jakarta.persistence.*;
import lombok.*;
import ting.li.accountsecurity.enums.AccountStatus;

@Entity
@Table(name="Account")
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="password")
    private String password;
    @Column(name="user_name")
    private String userName;
    @Column(name="account_status")
    private AccountStatus accountStatus;

}
