package com.example.reddit_clone.repositories;

import com.example.reddit_clone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {
}
