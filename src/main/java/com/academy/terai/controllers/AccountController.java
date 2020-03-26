package com.academy.terai.controllers;

import com.academy.terai.model.Account;
import com.academy.terai.service.AccountService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/all")
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping(value = "/{email}")
    public Account getAccountByEmail(@PathVariable("email") String email) throws NotFoundException {
        return accountService.findByEmail(email);
    }

    @GetMapping(value = "/order/reviewed")
    public List<Account> findAllByOrderByReviewedApplicationsDesc() {
        return accountService.findAllByOrderByReviewedApplicationsDesc();
    }

    @PostMapping
    ResponseEntity<HttpStatus> createAccount(@RequestBody Account account) throws KeyAlreadyExistsException, NotFoundException {
        accountService.addAccount(account);
        return new ResponseEntity(HttpStatus.valueOf(204));
    }

    @PutMapping("/{id}")
    ResponseEntity<HttpStatus> updateAccount(@RequestBody Account account, @PathVariable String id) throws NotFoundException {
        accountService.updateAccount(account, id);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    @DeleteMapping(value = "/{email}")
    public void deleteStudent(@PathVariable String email) throws NotFoundException {
        accountService.deleteAccount(accountService.findByEmail(email).getId());
    }
}