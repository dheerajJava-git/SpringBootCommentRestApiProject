package com.comments.restapi.service;

import com.comments.restapi.entity.Comments;
import com.comments.restapi.exception.CommentNotFoundException;
import com.comments.restapi.exception.DateNotFoundException;
import com.comments.restapi.exception.UsernameChangeException;
import com.comments.restapi.exception.UsernameNotFoundException;
import com.comments.restapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comments createComment(Comments comments) {
        comments.setDateofcomment(LocalDateTime.now());
        return commentRepository.save(comments);
    }

    @Override
    public List<Comments> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comments> getCommentsByUsername(String username) {
        List<Comments> comments = commentRepository.findByUsername(username);

        if (comments.isEmpty()) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }
        return comments;
    }

    @Override
    public List<Comments> getCommentsByDate(LocalDateTime date) {
        List<Comments> comments = commentRepository.findByDateofcomment(date);
        if (comments.isEmpty()) {
            throw new DateNotFoundException("No comments found for date: " + date);
        }
        return comments;
    }

    @Override
    public Comments updateComment(Long id, Comments comments) {

        Comments existingComment = commentRepository.findById(id).orElse(null);

        if (existingComment == null) {
            throw new CommentNotFoundException("Comment not found for id: " + id);
        }

        if (!existingComment.getUsername().equals(comments.getUsername())) {
            throw new UsernameChangeException("Username cannot be changed.");
        }
        existingComment.setComment(comments.getComment());
        comments.setDateofcomment(LocalDateTime.now());
        return commentRepository.save(existingComment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteAllComments() {
        commentRepository.deleteAll();
    }
}
