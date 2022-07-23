package com.example.reddit_clone.repositories;

import com.example.reddit_clone.entities.VerficationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifTokenRepository extends JpaRepository<VerficationToken , Long> {
}
