package com.codeup.springblogapp.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id // Set as Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Set to Auto-Increment
    private long id; // Set to BIGINT due to being long

    @Column(nullable = false,unique = true) // set to NOT NULL and is going to be unique
    private String username;

    @Column(nullable = false,unique = true) // set to NOT NULL and is going to be unique
    private String email;

    @Column // Will be hashed so worries on being unique
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") // 1 User can have many Posts (1-to-many)
    private List<Post> posts;

    // Constructors
    public User(){};

    public User(long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
