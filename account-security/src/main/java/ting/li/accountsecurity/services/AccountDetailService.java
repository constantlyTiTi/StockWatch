package ting.li.accountsecurity.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ting.li.accountsecurity.repository.UserRepsository;

@Service
@AllArgsConstructor
public class AccountDetailService implements UserDetailsService {
    private final UserRepsository userRepsository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
