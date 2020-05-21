package com.codeup.springblogapp.Controllers;

import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import com.codeup.springblogapp.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class PostController {

    // Dependency Injection
    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private final EmailService emailService;

    // Springs version of DaoFactory that uses the Repo(interface as a Dao)
    public PostController(PostRepository postRepo, UserRepository userRepo, EmailService emailService) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.emailService = emailService;
    }

////////////////////////////////  Show All  ///////////////////////////////////////////

    // Method for showing all posts
    @GetMapping({"/posts","/"})
    public String getPosts(Model model) {

        // Pass the master list of Posts to post/index html page
        model.addAttribute("posts", postRepo.findAll());

        // Go to to html
        return "posts/index";

    }

////////////////////////////////  Show Single  ////////////////////////////////////////

    // When url is visited
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)

    // Method that uses id passed from posts/index.html and casts it to long
    public String getPost(@PathVariable long id, Model model){

        // Find the post by id in the database
        Post post = postRepo.getOne(id);

        // User who is logged in
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Find out if user logged in is the user who created the Post
        if (isPostOwner(id,user)){
            post.getUser().setOwner(1);
        } else {
            post.getUser().setOwner(0);
        }

        // find the post by id in database and send variable Post to html
        model.addAttribute("post",post);
        return "posts/show";

    }

////////////////////////////////  Create Post  ////////////////////////////////////////

    // When url is visited
    @GetMapping("/posts/createPost")

    // Method that creates a blank post and adds creating user to it
    public String createPost(Model model) {
        // Create a blank post
        Post post = new Post();

        // Add Post to model to send to html
        model.addAttribute("post",post);

        // Go to html
        return "posts/create";
    }

    // When url is posted to
    @PostMapping("/posts/createPost")

    // Method that uses Post Object from the forms in the posts/create.html
    public String newPost(@ModelAttribute Post post){
        // get User who is creating Post and add it to Post object
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);

        // Save Post object to the database
        postRepo.save(post);

        // Sends email to user
        emailService.prepareAndSend(post, "you created a Post", "Title:" + post.getTitle() + "\nDescription: " + post.getDescription());
//        emailService.sendSimpleMessage(user.getEmail(),"You Created a Post","Post created. Test from Spring");

        // Send to Post index controller
        return "redirect:/posts";
    }

////////////////////////////////  Update Post  ////////////////////////////////////////

    // When url is visited
    @GetMapping("/posts/{id}/edit")

    // Method that uses id passed from posts/show.html
    public String editPost(@PathVariable long id, Model model) {

        // Store Post with id passed from html to variable
        Post post = postRepo.getOne(id);

        // Pass Post from database to html
        model.addAttribute("post", post);

        // Go to html
        return "posts/update";

    }

    // When url is posted to
    @PostMapping("/posts/{id}/edit")

    // Method that updates Post based on Post Object passed by form in posts/show.html
    public String editedPost(@ModelAttribute Post updatedPost,@PathVariable long id, Model model){
        // Get current User
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Add ID passed by url and above User to Post object passed over
        updatedPost.setId(id);
        updatedPost.setUser(user);

        // Save Updated Post to database
        postRepo.save(updatedPost);

        // Save updatedPost to model to send to html
        model.addAttribute("post",updatedPost);

        // Go to Show post Controller
        return "redirect:/posts/" + id;

    }

//////////////////////////  Delete Post  //////////////////////////////////////////////

    // When url is posted to
    @PostMapping("/posts/delete")

    // Method that deletes Post based on info POSTED by button in posts/show.html
    public String deletePost(@RequestParam long id){

        // Get Post by id from database and store to new Post
        Post post = postRepo.getOne(id);

        // Delete retrieved Post from database
        postRepo.delete(post);

        // Redirect to controller to repopulate posts on html
        return "redirect:/posts";
    }

    public Boolean isPostOwner(long id, User user){
        Post post = postRepo.getOne(id);
        return user.getUsername().equals( post.getUser().getUsername() );
    }

}

