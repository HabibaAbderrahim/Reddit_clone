package com.example.reddit_clone.entities;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subreddit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id ;
    @NotNull
    private  String name ;
    @NotNull
    @Lob
    private String decription ;

    //relations
    //post oneToMany
    //user ManyToOne
}
