package com.example.reddit_clone.repositories;

import com.example.reddit_clone.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment , Long> {
}
