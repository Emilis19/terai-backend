package com.academy.terai.service;

import com.academy.terai.exceptions.ApiRequestException;
import com.academy.terai.model.Account;
import com.academy.terai.model.Application;
import com.academy.terai.model.ApplicationStatus;
import com.academy.terai.model.Comment;
import com.academy.terai.model.request.ApplicationRequest;
import com.academy.terai.model.request.ApplicationUpdateRequest;
import com.academy.terai.model.request.CommentRequest;
import com.academy.terai.model.request.StatusChangeReqeust;
import com.academy.terai.model.response.ApplicationFullResponse;
import com.academy.terai.model.response.ApplicationHrResponse;
import com.academy.terai.repository.AccountRepository;
import com.academy.terai.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.servlet.http.HttpServletRequest;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JavaMailSender javaMailSender;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, JavaMailSender javaMailSender, AccountService accountService, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.applicationRepository = applicationRepository;
        this.javaMailSender = javaMailSender;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public List<ApplicationHrResponse> findAll() {
        List<Application> applications = applicationRepository.findAll();
        List<ApplicationHrResponse> responseList = new ArrayList<>();

        for (Application app : applications) {
            responseList.add(new ApplicationHrResponse(app.getId(), app.getFirstName(),app.getLastName(), app.getDateCreated(), app.getStatus()));
        }
        return responseList;
    }


    public ApplicationFullResponse findById(final String id) throws ApiRequestException {
        Application app = applicationRepository.findById(id).orElseThrow(() -> new ApiRequestException("Aplikacija neegzistuoja su id: " + id));
            ApplicationFullResponse fullResponse = new ApplicationFullResponse(app);
            return fullResponse;
    }

    public Application findByEmail(final String email) {
        return applicationRepository.findByEmail(email).orElseThrow(() -> new ApiRequestException("Aplikacija neegzistuoja su el. pastu: " + email));
    }

    public void updateApplication(final ApplicationUpdateRequest application, final String id) throws ApiRequestException {
        Application appToChange = applicationRepository.findById(id).orElseThrow(() -> new ApiRequestException("Aplikacija neegzistuoja su id: " + id));
        appToChange.changeApp(application);
        appToChange.setStatus(ApplicationStatus.FORM_RECEIVED.getName());
        appToChange.setDateCreated(new Date());
        applicationRepository.save(appToChange);
    }

    public void deleteApplication(final String id) throws ApiRequestException {
        if (!applicationRepository.findById(id).isPresent()){
            throw new ApiRequestException("Aplikacija neegzistuoja su id: " + id);
        }
        applicationRepository.deleteById(id);
    }
    public ApplicationFullResponse addApplication(final ApplicationRequest application) throws ApiRequestException {
        if (applicationRepository.findByEmail(application.getEmail()).isPresent()){
            throw new ApiRequestException("Toks email jau egzistuoja: " + application.getEmail());
        }
        String password = UUID.randomUUID().toString();
        System.out.println(password);
        Application app = new Application(application);
        app.setDateCreated(new Date());
        app.setPassword(passwordEncoder.encode(password));
        app.setStatus(ApplicationStatus.FORM_RECEIVED.getName());
        ApplicationFullResponse returnApplication = new ApplicationFullResponse(applicationRepository.save(app));

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(returnApplication.getEmail());

            msg.setSubject("Akademija");
            msg.setText("Sveiki " + returnApplication.getFirstName() + "\n Formą galite peržiūrėti prisijungę: https://terai-frontend-staging.herokuapp.com/login"
                    + "\n Jūsų slaptažodis: " + password);

            javaMailSender.send(msg);


        return returnApplication;
    }
    public void changeStatus (final StatusChangeReqeust reqeust) throws ApiRequestException {
        Application app = applicationRepository.findById(reqeust.getId()).orElseThrow(() -> new ApiRequestException("Aplikacija neegzistuoja su id: " + reqeust.getId()));
        // for now gonna leave as string, but if front changes tu ENUM, ill change to enum aswell
        app.setStatus(reqeust.getStatus());
        applicationRepository.save(app);
        if (reqeust.getStatus().equals("Priėmimas į akademiją patvirtintas")){
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(app.getEmail());

            msg.setSubject("Akademija");
            msg.setText("Sveiki " + app.getFirstName() + "\n Sveikiname patekus i IT Akademija!"
                    + "\n Tikimės, kad gerai praleisite laiką!");
            javaMailSender.send(msg);
        }
    }
    public void addComment (final CommentRequest request) throws ApiRequestException {
        Application app = applicationRepository.findById(request.getAppId()).orElseThrow(() -> new ApiRequestException("Aplikacija neegzistuoja su id: " + request.getAppId()));
        Account account = accountRepository.findById(request.getHrId()).orElseThrow(() -> new ApiRequestException("Vartotojas neegzistuoja su id: " + request.getHrId()));
        Comment comment = new Comment(request);
        comment.setHrName(account.getName());
        List<Comment> commentList = app.getComment();
        commentList.add(comment);
        app.setComment(commentList);
        applicationRepository.save(app);
        account.setReviewedApplications(account.getReviewedApplications() + 1);
        accountRepository.save(account);
    }

    public List<Comment> getComments(final String id){
        Application app = applicationRepository.findById(id).orElseThrow(() -> new ApiRequestException("Aplikacija neegzistuoja su id: " + id));
        List<Comment> comments = new ArrayList();
        comments = app.getComment();
        return comments;
    }
}
