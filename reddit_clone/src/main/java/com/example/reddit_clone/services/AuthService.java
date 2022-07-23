package com.example.reddit_clone.services;

import com.example.reddit_clone.DTO.RegisterRequest;
import com.example.reddit_clone.entities.User;
import com.example.reddit_clone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository ; //save user
    @Autowired
    private PasswordEncoder passwordEncoder ;

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



    }
}
