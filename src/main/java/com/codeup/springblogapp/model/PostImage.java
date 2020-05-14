package com.codeup.springblogapp.model;

import javax.persistence.*;

@Entity
@Table(name = "post_images")
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String path; // Path to the image

    @ManyToOne // Many images can be attached to one post
    @JoinColumn(name = "post_id")
    private Post post;

    // Constructors
    public PostImage(){}

    public PostImage(long id, String path, Post post) {
        this.id = id;
        this.path = path;
        this.post = post;
    }

    public PostImage(String path, Post post) {
        this.path = path;
        this.post = post;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
