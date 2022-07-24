package com.example.reddit_clone.services.auth;

import com.example.reddit_clone.DTO.RegisterRequest;
import com.example.reddit_clone.entities.NotificationMail;
import com.example.reddit_clone.entities.User;
import com.example.reddit_clone.entities.VerficationToken;
import com.example.reddit_clone.repositories.UserRepository;
import com.example.reddit_clone.repositories.VerifTokenRepository;
import com.example.reddit_clone.services.mails.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository ; //save user //better if we used constructor injection
    @Autowired
    private PasswordEncoder passwordEncoder ;//better if we used constructor injection
    @Autowired
    private VerifTokenRepository verifTokenRepository;
    @Autowired
    private MailService mailService ;

    @Transactional //DB
    public void signup(RegisterRequest registerRequest){

        User user = new User() ;
        //take registerrequest proprties and set them into user
        user.setEmail(registerRequest.getEmail());
        user.setUserName(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));//new Bcrypt
        user.setCreateDate(Instant.now());
        user.setEnabled(false);//once validated TRUE
        //save user to database
        userRepository.save(user) ;
        //call
        String token = generateVerificationToken(user);
       //Mail service after user activates mai : True , sendMail
        mailService.sendMail(new NotificationMail("Reddit Verify your mail addresse", user.getEmail(), "Click to the URl to activate your Reddit Account :" +
                "http://localhost:9090/api/authentification/account_verification" + "Token :" +token
        ));

    }

    private String generateVerificationToken(User user){
        VerficationToken verficationToken = new VerficationToken();
        String token = UUID.randomUUID().toString(); //generate random token bite of 8
        verficationToken.setToken(token);
        verficationToken.setUser(user);

        //save Token related to user into DB
        verifTokenRepository.save(verficationToken);
        return token ;

    }
}
