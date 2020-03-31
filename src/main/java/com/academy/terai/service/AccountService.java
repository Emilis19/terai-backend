package com.academy.terai.service;

import com.academy.terai.model.Account;
import com.academy.terai.model.Application;
import com.academy.terai.model.request.AccountRequest;
import com.academy.terai.model.response.AccountResponse;
import com.academy.terai.model.response.ApplicationHrResponse;
import com.academy.terai.repository.AccountRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AccountResponse> findAll() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountResponse> responseList = new ArrayList<>();

        for (Account acc : accounts) {
            responseList.add(new AccountResponse(acc));
        }
        return responseList;
    }

    public Account findByEmail(final String email) throws UsernameNotFoundException {
        return  this.accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email: " + email + " not found"));
    }

    public List<Account> findAllByOrderByReviewedApplicationsDesc() {
        return accountRepository.findAllByOrderByReviewedApplicationsDesc();
    }
    public void updateAccount(final Account account, final String id) throws NotFoundException {
        //change to orelsethrow
        if (!accountRepository.findById(id).isPresent()){
            throw new NotFoundException(id);
        }

        accountRepository.save(account);
    }
    public Account addAccount(final AccountRequest account) throws KeyAlreadyExistsException, NotFoundException {
//        if (accountRepository.findByEmail(account.getEmail()) != null){
//            throw new KeyAlreadyExistsException(account.getEmail());
//        }
        Account acc = new Account(account);
        acc.setLastLoggedIn(new Date());
        //TODO: change the naming convention to ENUM like
        acc.setReviewedApplications(0);
        acc.setPassword(passwordEncoder.encode(acc.getPassword()));
        return accountRepository.save(acc);
    }

    public void deleteAccount(final String id) {
        accountRepository.deleteById(id);
    }
}
