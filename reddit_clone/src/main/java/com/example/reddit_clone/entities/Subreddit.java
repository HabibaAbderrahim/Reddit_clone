package com.example.reddit_clone.entities;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subreddit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long subId ;
    @NotNull
    private  String name ;
    @NotNull
    @Lob
    private String decription ;

    //relations
    //post oneToMany bi
    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;
    //user ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId" , referencedColumnName = "userId")
    private User user ;
}
