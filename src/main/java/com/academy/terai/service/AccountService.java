package com.academy.terai.service;

import com.academy.terai.Model.Account;
import com.academy.terai.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

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
        accountRepository.save(account);
    }

    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }
}
