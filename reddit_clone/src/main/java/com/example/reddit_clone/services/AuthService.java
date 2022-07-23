package com.example.reddit_clone.services;

import com.example.reddit_clone.DTO.RegisterRequest;
import com.example.reddit_clone.entities.User;
import com.example.reddit_clone.entities.VerficationToken;
import com.example.reddit_clone.repositories.UserRepository;
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
    private VerficationToken verficationToken ;

    @Transactional //DB
    public void signup(RegisterRequest registerRequest){

        User user = new User() ;
        //take registerrequest proprties and set them into user
        user.setEmail(registerRequest.getEmail());
        user.setUserName(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));//new Bcrypt
        user.setCreateDate(Instant.now());
        user.setEnabled(false);//once validated TRUE

        userRepository.save(user) ;

        //call
        generateVerificationToken(user);

    }

    private void generateVerificationToken(User user){
        VerficationToken verficationToken = new VerficationToken();
        String token = UUID.randomUUID().toString(); //generate random token bite of 8
        verficationToken.setToken(token);
        verficationToken.setUser(user);

    }
}
