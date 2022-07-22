package com.example.reddit_clone.entities;

//spacial type of class that defines a set of variables
//JAVA CONSTANT ARE ALL SPELLED UPCASED
//ENUM CONSTANT ARE IMPLICITLY BOTH FINAL / STATIC
//
public enum  VoteType {
    UPVOTE(1) ,DOWNVOTE(-1)
    ;


    VoteType(int direction) {
    }
}

/*
* public class VoteType {
* public static final int UPVOTE = 1 ;
* public static final int DOWNVOTE =-1
* }
* //call
* VoteType v = VoteType.UPVOTE ;
* */