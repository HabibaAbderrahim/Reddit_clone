package com.example.reddit_clone.controller;

import com.example.reddit_clone.DTO.RegisterRequest;
import com.example.reddit_clone.entities.User;
import com.example.reddit_clone.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
///api/auth/signup
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                HttpStatus.OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verfAccount(@PathVariable String token){
        authService.accountVerification(token);
        return  new ResponseEntity<>("Verfied Account", HttpStatus.OK);

    }
}
