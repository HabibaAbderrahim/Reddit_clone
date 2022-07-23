package com.example.reddit_clone.repositories;

import com.example.reddit_clone.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubRepository extends JpaRepository<Post , Long> {
}
