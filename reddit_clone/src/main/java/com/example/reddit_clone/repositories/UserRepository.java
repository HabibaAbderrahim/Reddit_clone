package com.example.reddit_clone.repositories;

import com.example.reddit_clone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {

    public Optional<User> findByUsername(String username);
}
