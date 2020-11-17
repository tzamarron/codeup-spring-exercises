package com.codeup.springblogapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WoWController {
    // Get for game page
    @GetMapping("/wow")
    public String startGame(){
        return "wow/prerares";
    }
}
