package com.academy.terai.service;

import com.academy.terai.model.Account;
import com.academy.terai.model.Application;
import com.academy.terai.model.CustomUserDetails;
import com.academy.terai.repository.AccountRepository;
import com.academy.terai.repository.ApplicationRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private AccountRepository accountRepository;
    private ApplicationRepository applicationRepository;

    public CustomUserDetailsService(AccountRepository accountRepository, ApplicationRepository applicationRepository) {
        this.accountRepository = accountRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Application app;
        Account account;
        CustomUserDetails details;
        if(applicationRepository.findByEmail(email).isPresent()){
            app = applicationRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email: " + email + " not found"));
            details = new CustomUserDetails(app.getId(), app.getEmail(), app.getPassword(), new ArrayList<>());

        }else{
            account = accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email: " + email + " not found"));
            details = new CustomUserDetails(account.getId(), account.getEmail(), account.getPassword(), account.getRoles());
        }
        return  details;
    }
}
