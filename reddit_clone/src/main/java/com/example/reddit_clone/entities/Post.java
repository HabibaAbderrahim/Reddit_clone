package com.example.reddit_clone.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId ;
    @NotNull
    private String name ;
    @Nullable
    private String url ;
    @Nullable
    @Lob
    private String description ;
    private Integer voteCount ;
    private Instant createdDate ;
    //Relations
    //uer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId" , referencedColumnName = "userId")
    private  User user ;
    //subReddit bidir
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subredditId" , referencedColumnName = "subId")
    private Subreddit subreddit ;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments ;
}
