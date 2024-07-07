package com.comments.restapi.repository;

import com.comments.restapi.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

    List<Comments> findByDateofcomment(LocalDateTime date);

    List<Comments> findByUsername(String username);
}
