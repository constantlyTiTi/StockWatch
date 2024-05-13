package ting.li.accountsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ting.li.accountsecurity.dao.AccountInfo;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AccountInfo, UUID> {
    @Query("Select ac from account_info ac where userName = :username")
    Optional<AccountInfo> findByUserName(String username);
}
