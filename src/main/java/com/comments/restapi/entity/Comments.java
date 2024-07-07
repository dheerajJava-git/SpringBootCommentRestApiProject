package com.comments.restapi.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comments {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "dateofcomment", nullable = false)
    private LocalDateTime dateofcomment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        if (comment == null || comment.trim().isEmpty()) {
            throw new IllegalArgumentException("comment cannot be null or empty");
        }
        this.comment = comment;
    }

    public LocalDateTime getDateofcomment() {
        return dateofcomment;
    }

    public void setDateofcomment(LocalDateTime dateofcomment) {
        this.dateofcomment = dateofcomment;
    }
}
