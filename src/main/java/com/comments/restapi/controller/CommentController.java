package com.comments.restapi.controller;

import com.comments.restapi.entity.Comments;
import com.comments.restapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Comments> getCommentsByUsername(@RequestParam(value = "username") String username) {
        return commentService.getCommentsByUsername(username);
    }

    @GetMapping("/search/date")
    public List<Comments> getCommentsByDate(@RequestParam(value = "date") LocalDateTime date) {
        return commentService.getCommentsByDate(date);
    }

    @PutMapping("/{id}")
    public Comments updateComment(@PathVariable("id") Long id, @RequestBody Comments comments) {
        return commentService.updateComment(id, comments);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
    }

    @DeleteMapping
    public void deleteAllComments() {
        commentService.deleteAllComments();
    }
}
