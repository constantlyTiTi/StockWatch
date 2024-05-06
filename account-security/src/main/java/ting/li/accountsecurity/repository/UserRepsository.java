package ting.li.accountsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ting.li.accountsecurity.dao.Account;

import java.util.UUID;

@Repository
public interface UserRepsository extends JpaRepository<Account, UUID> {
}
