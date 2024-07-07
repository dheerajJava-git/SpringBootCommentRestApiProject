package com.comments.restapi.service;

import com.comments.restapi.entity.Comments;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentService {

    Comments createComment(Comments comments);

    List<Comments> getAllComments();

    List<Comments> getCommentsByUsername(String username);

    List<Comments> getCommentsByDate(LocalDateTime date);

    Comments updateComment(Long id, Comments commentsDetails);

    void deleteComment(Long id);

    void deleteAllComments();

}
