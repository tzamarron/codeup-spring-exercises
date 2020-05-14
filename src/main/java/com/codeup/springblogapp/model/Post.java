package com.codeup.springblogapp.model;

import javax.persistence.*;

@Entity
public class Post {
    @Id // Set as Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Set to Auto-Increment
    private long id; // Set to BIGINT due to being long

    @Column(nullable = false, length = 100) // set to NOT NULL and max length of 100 characters
    private String title; // Set to VARCHAR due to being a String

    @Column(nullable = false, columnDefinition = "TEXT") // set to NOT NULL and TEXT type
    private String description; // Due to above definition it is set to TEXT and not VARCHAR

    // Constructors
    public Post(){};

    public Post(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
