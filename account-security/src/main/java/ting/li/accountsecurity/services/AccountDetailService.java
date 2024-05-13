package ting.li.accountsecurity.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ting.li.accountsecurity.dao.AccountInfo;
import ting.li.accountsecurity.models.AccountPrincipal;
import ting.li.accountsecurity.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AccountInfo> account = userRepository.findByUserName(username);

        if (account.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new AccountPrincipal(account.get());

    }
}
