package com.oracle.bowling.model;

import java.util.Date;

public class Post {
    private Long id;
    private String title;
    private String body;
    private ScoreResponse author;
    private Date date = new Date();


    public Post(Long id, String title, String body, ScoreResponse author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ScoreResponse getAuthor() {
        return author;
    }

    public void setAuthor(ScoreResponse author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
