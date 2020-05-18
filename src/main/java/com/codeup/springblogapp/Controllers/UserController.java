package com.codeup.springblogapp.Controllers;

import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    // Dependency Injection
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;

    // Springs version of DaoFactory that uses the Repo(interface as a Dao)
    public UserController(PasswordEncoder passwordEncoder, UserRepository userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userRepo.save(user);
        return "redirect:/login";
    }
}

