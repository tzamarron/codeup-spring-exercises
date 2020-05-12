package com.codeup.springblogapp.Controllers;

import com.codeup.springblogapp.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    // Method for showing all posts
    @GetMapping("/posts")
    public String getPosts(Model model){
        List<Post> posts = new ArrayList<>();
        Post test = new Post("First Awesome Title", "First mind-blowing description!");
        posts.add (test);
        test = new Post("Second Awesome Title", "Second mind-blowing description!");
        posts.add(test);
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    // Method for showing a single post
    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable int id, Model model){
        Post post = new Post("Awesome Title", "Mind-blowing Description!");
        model.addAttribute("post",post);
        return "posts/show";
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
