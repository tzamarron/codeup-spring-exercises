package com.codeup.springblogapp.Controllers;

import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    // Dependency Injection
    private PostRepository postRepo;
    private UserRepository userRepo;

    // Springs version of DaoFactory that uses the Repo(interface as a Dao)
    public PostController(PostRepository postRepo, UserRepository userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

////////////////////////////////  Show All  ///////////////////////////////////////////

    // Method for showing all posts
    @GetMapping("/posts")
    public String getPosts(Model model) {

        // Pass the master list of Posts to post/index html page
        model.addAttribute("posts", postRepo.findAll());

        // Go to to html
        return "posts/index";

        // Another example
//        String postString = "<div>";
//        for (Post post : this.postRepo.findAll()) {
//            postString += "<p>" + post.getTitle() + " </p> " + "<p>" + post.getDescription() + "</p>";
//        }
//        postString += "</div>";
    }

////////////////////////////////  Show Single  ///////////////////////////////////////////

    // When url is visited
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)

    // Method that uses id passed from posts/index.html and casts it to long
    public String getPost(@PathVariable(name = "id") long id, Model model){

        // find the post by id in database and send variable Post to html
        model.addAttribute("post",postRepo.getOne(id));

        // Go to html
        return "posts/show";
    }

////////////////////////////////  Create Post  ///////////////////////////////////////////

    // When url is visited
    @GetMapping("/posts/createPost")
    public String createPost() {
        // Go to html
        return "posts/create";
    }

    // When url is posted to
    @PostMapping("/posts/createPost")

    // Method that uses data POSTed from the forms in the posts/create.html
    public String newPost(@RequestParam(name="postTitle") String title, @RequestParam(name="postDescription") String description) {

        // Temp Code
        User user = new User(1,"uprizin","ted@email.com");

        // create a new Post with data sent
       Post post = new Post(title,description,user);

       // Save new Post to database using postRepo
       postRepo.save(post);

       // redirect to Controller to repopulate data to html
       return "redirect:/posts";
    }

////////////////////////////////  Update Post  ///////////////////////////////////////////

    // When url is visited
    @GetMapping("/posts/update/{id}")

    // Method that uses id passed from posts/show.html
    public String editPost(@PathVariable(name="id") long id, Model model) {

        // Store Post with id passed from html to variable
        Post post = postRepo.getOne(id);

        // Pass Post from database to html
        model.addAttribute("post", post);

        // Go to html
        return "/posts/update";

    }

    // When url is posted to
    @PostMapping("/posts/update/{id}")

    // Method that updates Post based on info POSTed by form in posts/show.html
    public String updatedPost(@RequestParam(name="postTitle") String title, @RequestParam(name="postDescription") String description, @PathVariable(name="id") long id, Model model) {

        // Find Post by id passed and store to new Post
        Post post = postRepo.findById(id);

        // Set found post's properties to new properties
        post.setTitle(title);
        post.setDescription(description);

        // Save this(post from database) to database with the new set properties
        post = this.postRepo.save(post);

        // Store Post to model to send to html to use
        model.addAttribute("post",post);

        // Redirect to controller to repopulate post with new data in html
        return "redirect:/posts/" + post.getId();
    }

////////////////////////////////  Delete Post  /////////////////////////////////////////////////

    // When url is posted to
    @PostMapping("/posts/delete")

    // Method that deletes Post based on info POSTED by button in posts/show.html
    public String deletePost(@RequestParam(name="id") long id){

        // Get Post by id from database and store to new Post
        Post post = postRepo.getOne(id);

        // Delete retrieved Post from database
        postRepo.delete(post);

        // Redirect to controller to repopulate posts on html
        return "redirect:/posts";
    }
}
