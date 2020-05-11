package com.codeup.springblogapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String getPosts(){
        return "This is where we see all the posts";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getPostById(@PathVariable int id){
        return "This is where we see an individual post based on ID passed";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "This is where we view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String post(){
        return "This is where we submit our post after we create it";
    }
}
