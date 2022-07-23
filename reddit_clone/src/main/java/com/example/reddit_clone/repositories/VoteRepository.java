package com.example.reddit_clone.repositories;

import com.example.reddit_clone.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
