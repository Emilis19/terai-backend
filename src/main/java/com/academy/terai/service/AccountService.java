package com.academy.terai.service;

import com.academy.terai.Model.Account;
import com.academy.terai.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public List<Account> findAllByOrderByReviewedApplicationsDesc() {
        return accountRepository.findAllByOrderByReviewedApplicationsDesc();
    }

    public void saveOrUpdateAccount(Account account) {
        account.setLastLoggedIn(new Date());
        accountRepository.save(account);
    }

    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }
}
