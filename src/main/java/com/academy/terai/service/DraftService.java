package com.academy.terai.service;
import com.academy.terai.exceptions.ApiRequestException;
import com.academy.terai.model.Draft;
import com.academy.terai.model.request.DraftRequest;
import com.academy.terai.repository.ApplicationRepository;
import com.academy.terai.repository.DraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DraftService {
    private final ApplicationRepository applicationRepository;
    private final DraftRepository draftRepository;
    private final JavaMailSender javaMailSender;
    private final ApplicationService applicationService;

    @Autowired
    public DraftService(ApplicationRepository applicationRepository, DraftRepository draftRepository, JavaMailSender javaMailSender, ApplicationService applicationService) {
        this.applicationRepository = applicationRepository;
        this.draftRepository = draftRepository;
        this.javaMailSender = javaMailSender;
        this.applicationService = applicationService;
    }
    public void addDraft (final DraftRequest request){
        if (applicationRepository.findByEmail(request.getEmail()).isPresent()||draftRepository.findByEmail(request.getEmail()).isPresent()){
            return;
        }
        Draft draft = new Draft(request);
        draft.setDateCreated(new Date());
        draft.setReminderSent(false);
        draftRepository.save(draft);
    }
    @Scheduled(fixedRateString = "PT1S")
    public void sendReminder(){
        Date markerDate = new Date(new Date().getTime());
        List<Draft> draftList = draftRepository.findAllByDateCreatedIsBeforeAndReminderSentFalse(markerDate);
        for( Draft draft: draftList){
            if(!applicationRepository.findByEmail(draft.getEmail()).isPresent()){
                draft.setReminderSent(true);
                draftRepository.save(draft);
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo(draft.getEmail());
                msg.setSubject("IT Akademija tavęs laukia");
                msg.setText("Sveiki " + draft.getFirstName() + "\n Nebaigei pildyti registracijos formos į IT Akademiją! Nepraleisk progos tobulėti!\n Užsiregistruok čia: https://terai-frontend-staging.herokuapp.com/registration/0");
                javaMailSender.send(msg);
            }else{
                draftRepository.deleteById(draft.getId());
            }
        }
    }
}
