package com.crud.hotels.service;

import com.crud.hotels.config.AdminConfig;
import com.crud.hotels.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://pawelski85.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview", previewMessage(message));
        context.setVariable("goodbye", adminConfig.getAdminGoodbye());
        context.setVariable("company", companyConfig.getCompanyName());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    private String previewMessage(String message) {
        String[] words = message.split(" ");
        return words[0] + " " + words[1] + " " + words[2] + words[3] + words[4] +"(...)";
    }
}
