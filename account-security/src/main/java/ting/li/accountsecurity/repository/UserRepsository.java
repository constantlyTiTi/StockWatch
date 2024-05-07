package ting.li.accountsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ting.li.accountsecurity.dao.Account;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepsository extends JpaRepository<Account, UUID> {
    @Query("Select ac from account ac where user_name = ?1")
    Optional<Account> findByUserName(String username);
}
