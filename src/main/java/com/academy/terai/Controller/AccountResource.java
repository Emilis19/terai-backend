package com.academy.terai.Controller;

import com.academy.terai.Model.Account;
import com.academy.terai.Repository.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountResource {
    private AccountRepository accountRepository;

    public AccountResource(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @GetMapping("/all")
    public List<Account> getAll(){
        return accountRepository.findAll();
    }
}