package com.example.reddit_clone.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NotificationMail {
    private String subject ;
    private String recipient ;
    private String body ;
}
