package com.academy.terai.security;

import com.academy.terai.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AccountDetailsService implements UserDetailsService {

    private AccountRepository accountRepository;

    public AccountDetailsService(AccountRepository accounts) {
        this.accountRepository = accounts;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) this.accountRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Email: " + email + " not found"));
    }
}
