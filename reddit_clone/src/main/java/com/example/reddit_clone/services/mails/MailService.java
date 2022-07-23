package com.example.reddit_clone.services.mails;

import com.example.reddit_clone.DTO.SpringRedditException;
import com.example.reddit_clone.entities.NotificationMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Slf4j //log
public class MailService {

    @Autowired
    private MailContetntBuilder mailContetntBuilder ; //msg
    @Autowired
    private JavaMailSender mailSender ; //mail

    public void sendMail(NotificationMail notificationMail){
            MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springreddit@email.com");
            messageHelper.setTo(notificationMail.getRecipient());
            messageHelper.setSubject(notificationMail.getSubject());
            messageHelper.setText(notificationMail.getBody());
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent with success !");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new SpringRedditException("Exception occurred when sending mail to " + notificationMail.getRecipient(), e);
        }
    }

}
