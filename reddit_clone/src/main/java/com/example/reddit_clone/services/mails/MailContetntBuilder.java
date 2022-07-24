package com.example.reddit_clone.services.mails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContetntBuilder {

    @Autowired
    private TemplateEngine templateEngine ;//thym

    //email msg we want to sent to the user as input
    public String build(String message) {
        Context context = new Context();
        context.setVariable("msg", message);
        return templateEngine.process("mailTemplate", context);
    }




}
