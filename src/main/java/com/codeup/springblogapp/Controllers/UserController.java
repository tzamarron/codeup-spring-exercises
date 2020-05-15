package com.codeup.springblogapp.Controllers;

import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    // Dependency Injection
    private UserRepository userRepo;

    // Springs version of DaoFactory that uses the Repo(interface as a Dao)
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
}
