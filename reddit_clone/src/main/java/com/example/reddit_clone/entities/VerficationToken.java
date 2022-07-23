package com.example.reddit_clone.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token")
public class VerficationToken {

    private Long id ;
    private String token ;
    private Instant expiryDate ;
    //user
    @OneToOne
    private User user ;
}
