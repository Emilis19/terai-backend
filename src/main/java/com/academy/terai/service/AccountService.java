package com.academy.terai.service;

import com.academy.terai.exceptions.ApiRequestException;
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

    public Account findByEmail(final String email) throws ApiRequestException {
        return  this.accountRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Toks el. pastas sistemoje neegzistuoja"));
    }

    public List<Account> findAllByOrderByReviewedApplicationsDesc() {
        return accountRepository.findAllByOrderByReviewedApplicationsDesc();
    }
    public void updateAccount(final Account account, final String id) throws ApiRequestException {
        //change to orelsethrow
        if (!accountRepository.findById(id).isPresent()){
            throw new ApiRequestException("Akauntas neegzistuoja su id: " + id);
        }

        accountRepository.save(account);
    }
    public Account addAccount(final AccountRequest account) throws ApiRequestException {
        if (accountRepository.findByEmail(account.getEmail()).isPresent()){
            throw new ApiRequestException("Toks el. pastas sistemoje jau yra");
        }
        Account acc = new Account(account);
        acc.setLastLoggedIn(new Date());
        //TODO: change the naming convention to ENUM like
        acc.setReviewedApplications(0);
        acc.setPassword(passwordEncoder.encode(acc.getPassword()));
        return accountRepository.save(acc);
    }

    public void deleteAccount(final String id) throws ApiRequestException{
        if (!accountRepository.findById(id).isPresent()){
            throw new ApiRequestException("Akauntas neegzistuoja su id: " + id);
        }
        accountRepository.deleteById(id);
    }

    public Account findById(final String id) throws ApiRequestException {
        return  this.accountRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Toks id neegzistuoja"));
    }
}
