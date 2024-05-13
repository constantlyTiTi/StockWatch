package ting.li.accountsecurity.dao;

import jakarta.persistence.*;
import lombok.*;
import ting.li.accountsecurity.enums.AccountStatus;

import java.util.UUID;

@Entity(name="account_info")
@Table(name="account_info")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name="password")
    private String password;

    @Column(name="user_name")
    private String userName;

    @Column(name="account_status")
    private AccountStatus accountStatus;

}
