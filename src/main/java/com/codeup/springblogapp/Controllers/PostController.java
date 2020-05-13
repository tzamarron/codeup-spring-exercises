package com.codeup.springblogapp.Controllers;

import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    // Dependency Injection
    private PostRepository postRepo;

    public PostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

////////////////////////////////  Show All  ///////////////////////////////////////////
    // Method for showing all posts
    @GetMapping("/posts")
    public String getPosts(Model model) {
        // List Bucket for Posts
        List<Post> posts = new ArrayList<>();

//        String postString = "<div>";
//        for (Post post : this.postRepo.findAll()) {
//            postString += "<p>" + post.getTitle() + " </p> " + "<p>" + post.getDescription() + "</p>";
//        }
//        postString += "</div>";


        // For each Post in the master list of Post
        // add the Post to the List bucket
        for (Post post : postRepo.findAll()) {
            posts.add(new Post(
                    post.getId(),
                    post.getTitle(),
                    post.getDescription()
            ));
        }

        // Pass the List bucket of Posts to post/index html page
        model.addAttribute("posts", posts);
        return "posts/index";
    }

////////////////////////////////  Show Single  ///////////////////////////////////////////
    // Method for showing a single post
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public String getPost(@PathVariable(name = "id") long id, Model model){
        Post post = postRepo.getOne(id);
        model.addAttribute("post",post);
        return "posts/show";
    }

////////////////////////////////  Create Post Get  ///////////////////////////////////////////
    @GetMapping("/posts/createPost")
    public String createPost() {
        return "posts/create";
    }

////////////////////////////////  Create Post Post  ///////////////////////////////////////////
    @PostMapping("/posts/createPost")
    public String newPost(@RequestParam(name="postTitle") String title, @RequestParam(name="postDescription") String description, Model model) {
       Post post = new Post(title,description);
       this.postRepo.save(post);
       model.addAttribute("post", post);
       return "redirect:/posts";
    }

////////////////////////////////  Update Post Get  ///////////////////////////////////////////
    @GetMapping("/posts/update/{id}")
    public String editPost(@PathVariable(name="id") long id, Model model) {
        Post post = postRepo.getOne(id);
        model.addAttribute("post", post);
        return "/posts/update";

    }

////////////////////////////////  Update Post Post  ///////////////////////////////////////////
    @PostMapping("/posts/update/{id}")
    public String updatedPost(@RequestParam(name="postTitle") String title, @RequestParam(name="postDescription") String description, @PathVariable(name="id") long id, Model model) {
        Post post = new Post(title, description);
        postRepo.save(post);
        return "redirect:/posts";
    }

////////////////////////////////  Delete Post  /////////////////////////////////////////////////
    @PostMapping("/posts/delete")
    public String deletePost(@RequestParam(name="id") long id, Model model){
        Post post = postRepo.getOne(id);
        postRepo.delete(post);
        return "redirect:/posts";
    }
}
