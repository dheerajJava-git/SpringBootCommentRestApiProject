package com.comments.restapi.controller;

import com.comments.restapi.entity.Comments;
import com.comments.restapi.exception.CommentNotFoundException;
import com.comments.restapi.exception.DateNotFoundException;
import com.comments.restapi.exception.UsernameChangeException;
import com.comments.restapi.exception.UsernameNotFoundException;
import com.comments.restapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v2/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comments> createComment(@RequestBody Comments comments) {
        Comments createdComment = commentService.createComment(comments);
        return ResponseEntity.ok(createdComment);
    }

    @GetMapping
    public ResponseEntity<List<Comments>> getAllComments() {
        List<Comments> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/search/username")
    public ResponseEntity<?> getCommentsByUsername(@RequestParam(value = "username") String username) {
        try {
            List<Comments> comments = commentService.getCommentsByUsername(username);
            return ResponseEntity.ok(comments);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search/date")
    public ResponseEntity<?> getCommentsByDate(@RequestParam(value = "date") LocalDateTime date) {
        try {
            List<Comments> comments = commentService.getCommentsByDate(date);
            return ResponseEntity.ok(comments);
        } catch (DateNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long id, @RequestBody Comments comments) {
        try {
            Comments updatedComment = commentService.updateComment(id, comments);
            return ResponseEntity.ok(updatedComment);
        } catch (CommentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UsernameChangeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.ok("Comment with id " + id + " deleted successfully");
        } catch (CommentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllComments() {
        commentService.deleteAllComments();
        return ResponseEntity.ok("All comments deleted successfully");
    }

}
