package com.academy.terai.Controller;

import com.academy.terai.Model.Account;
import com.academy.terai.Repository.AccountRepository;
import com.academy.terai.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountResource {
    private final AccountService accountService;
    @Autowired
    public AccountResource(AccountService accountService){
        this.accountService = accountService;
    }
    @GetMapping(value = "/all")
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping(value = "/{email}")
    public Account getAccountByEmail(@PathVariable("email") String email) {
        return accountService.findByEmail(email);
    }

    @GetMapping(value = "/orderByReviewedApplications")
    public List<Account> findAllByOrderByReviewedApplicationsDesc() {
        return accountService.findAllByOrderByReviewedApplicationsDesc();
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> saveOrUpdateAccount(@RequestBody Account account) {
        accountService.saveOrUpdateAccount(account);
        return new ResponseEntity("Account added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{email}")
    public void deleteStudent(@PathVariable String email) {
        accountService.deleteAccount(accountService.findByEmail(email).getId());
    }
}