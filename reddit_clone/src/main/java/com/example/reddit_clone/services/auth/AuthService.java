package com.example.reddit_clone.services.auth;

import com.example.reddit_clone.DTO.LoginRequest;
import com.example.reddit_clone.DTO.RegisterRequest;
import com.example.reddit_clone.DTO.SpringRedditException;
import com.example.reddit_clone.entities.NotificationMail;
import com.example.reddit_clone.entities.User;
import com.example.reddit_clone.entities.VerficationToken;
import com.example.reddit_clone.repositories.UserRepository;
import com.example.reddit_clone.repositories.VerifTokenRepository;
import com.example.reddit_clone.services.mails.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
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
    @Autowired
    private AuthenticationManager   authenticationManager ;



    @Transactional //DB
    public void signup(RegisterRequest registerRequest){
        //take registerrequest proprties and set them into user
        //save user to database
        //call
       //Mail service after user activates mai : True , sendMail
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreateDate(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationMail("Activate your Reddit Account",
                user.getEmail(),
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + "Token:" + token));

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


    public void accountVerification (String token){
      //if token do not exists return Exp
          Optional<VerficationToken> verficationToken = verifTokenRepository.findByToken(token);
          verficationToken.orElseThrow(()-> new SpringRedditException("Invalid Token !"));
          //after validation Enable that user
          findUserAndEnable(verficationToken.get());

    }


    @Transactional //DB
    public void findUserAndEnable(VerficationToken verficationToken){
        User user = verficationToken.getUser();
        //find user
        userRepository.findById(user.getUserId()).orElseThrow(()-> new SpringRedditException("User not Found !"));
        user.setEnabled(true);
        userRepository.save(user);

    }

    public void login (LoginRequest loginRequest){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));



    }
}
