package com.codeup.springblogapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post {
    @Id // Set as Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Set to Auto-Increment
    private long id; // Set to BIGINT due to being long

    @Column(nullable = false, length = 100) // set to NOT NULL and max length of 100 characters
    private String title; // Set to VARCHAR due to being a String

    @Column(columnDefinition = "TEXT") // set to NOT NULL and TEXT type
    private String description; // Due to above definition it is set to TEXT and not VARCHAR

    @ManyToOne // One post will be assigned to only one User (1-to-1) Also acts as foreign key
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post") // each post can have many images
    private List<PostImage> images;

    // many posts can be mapped to many categories (Many-2-Many)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            // name of the intermediate table
            name = "post_categories",
            // name for the id of this model JoinColumns
            joinColumns = {@JoinColumn(name = "post_id")},
            // name for the if of the related model inverse JoinColumns
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> categories;

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
