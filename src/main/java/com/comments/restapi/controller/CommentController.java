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
    public Comments createComment(@RequestBody Comments comments) {
        return commentService.createComment(comments);
    }

    @GetMapping
    public List<Comments> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/search/username")
    public ResponseEntity<String> getCommentsByUsername(@RequestParam(value = "username") String username) {

        try {
            commentService.getCommentsByUsername(username);
            return ResponseEntity.ok("Comments fetched successfully by username");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }


    @GetMapping("/search/date")
    public ResponseEntity<String> getCommentsByDate(@RequestParam(value = "date") LocalDateTime date) {

        try {
            commentService.getCommentsByDate(date);
            return ResponseEntity.ok("Comments fetched successfully by date");
        } catch (DateNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateComment(@PathVariable("id") Long id, @RequestBody Comments comments) {
        try {
            commentService.updateComment(id, comments);
            return ResponseEntity.ok("Comment updated successfully");
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
            return ResponseEntity.ok("Comment delete successfully");
        } catch (CommentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping
    public void deleteAllComments() {
        commentService.deleteAllComments();
    }
}
