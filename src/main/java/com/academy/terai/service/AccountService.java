package com.academy.terai.service;

import com.academy.terai.Model.Account;
import com.academy.terai.Repository.AccountRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
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

    public Account findByEmail(String email) throws NotFoundException {
        return accountRepository.findByEmail(email);
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
    public Account addAccount(final Account account) throws KeyAlreadyExistsException, NotFoundException {
        if (accountRepository.findByEmail(account.getEmail()) != null){
            throw new KeyAlreadyExistsException(account.getEmail());
        }
        account.setLastLoggedIn(new Date());
        //TODO: change the naming convention to ENUM like
        account.setReviewedApplications(0);
        return accountRepository.save(account);
    }

    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }
}
