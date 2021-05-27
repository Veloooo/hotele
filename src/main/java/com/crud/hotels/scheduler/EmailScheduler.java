package com.crud.hotels.scheduler;

import com.crud.hotels.config.AdminConfig;
import com.crud.hotels.domain.Mail;
import com.crud.hotels.repository.HotelRepository;
import com.crud.hotels.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";
    private final SimpleEmailService simpleEmailService;
    private final HotelRepository hotelRepository;
    private final AdminConfig adminConfig;
    private String TASK="hotels";

    @Scheduled(cron = "0 23 16 * * *")
    public void sendInformationEmail() {
        long size = hotelRepository.count();
        if (size==1){
            this.TASK="task";
        }
        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "Currently in database you got: " + size + TASK,
                        null
                )
        );
    }
}
