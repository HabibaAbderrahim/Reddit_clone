package com.example.reddit_clone.DTO;


import org.springframework.mail.MailException;

//custom expections for user : like 404 ect ...
//and they are common
public class SpringRedditException extends RuntimeException {
    public SpringRedditException(String exMsg){
        super(exMsg);


    }



}
