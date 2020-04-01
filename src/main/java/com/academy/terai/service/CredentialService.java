package com.academy.terai.service;

import com.academy.terai.exceptions.ApiRequestException;
import com.academy.terai.model.Account;
import com.academy.terai.model.Application;
import com.academy.terai.model.request.ForgotPasswordRequest;
import com.academy.terai.repository.AccountRepository;
import com.academy.terai.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CredentialService {
    private AccountRepository accountRepository;
    private ApplicationRepository applicationRepository;
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CredentialService(AccountRepository accountRepository, ApplicationRepository applicationRepository, JavaMailSender javaMailSender, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.applicationRepository = applicationRepository;
        this.javaMailSender = javaMailSender;
        this.passwordEncoder = passwordEncoder;
    }
    public void sendNewPassword(final ForgotPasswordRequest forgotPasswordRequest) throws ApiRequestException{
        String password = UUID.randomUUID().toString();
        if(applicationRepository.findByEmail(forgotPasswordRequest.getEmail()).isPresent()){
            Application app = applicationRepository.findByEmail(forgotPasswordRequest.getEmail()).orElseThrow(() -> new ApiRequestException("Vartotojas " + forgotPasswordRequest.getEmail() +" neegzistuoja"));
            app.setPassword(passwordEncoder.encode(password));
            applicationRepository.save(app);

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(app.getEmail());
            msg.setSubject("Akademija");
            msg.setText("Sveiki " + app.getFirstName() + "\n Jusu naujas slaptazodis: " + password);
            javaMailSender.send(msg);

        }else if(accountRepository.findByEmail(forgotPasswordRequest.getEmail()).isPresent()){
            Account acc =accountRepository.findByEmail(forgotPasswordRequest.getEmail()).orElseThrow(() -> new ApiRequestException("Vartotojas " + forgotPasswordRequest.getEmail() +" neegzistuoja"));
            acc.setPassword(passwordEncoder.encode(password));
            accountRepository.save(acc);

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(acc.getEmail());
            msg.setSubject("Akademija");
            msg.setText("Sveiki " + acc.getName() + "\n Jusu naujas slaptazodis: " + password);
            javaMailSender.send(msg);
        }else{
            throw new ApiRequestException("Vartotojas " + forgotPasswordRequest.getEmail() +" neegzistuoja");
        }
    }

}
