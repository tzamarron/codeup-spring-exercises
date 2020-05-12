package com.codeup.springblogapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String startGame(){
        return "diceGame";
    }

    @PostMapping("/roll-dice")
    public String resultGame(@RequestParam(name = "guess") int guess, Model model){
        int dice = (int) Math.floor((Math.random() * 6) + 1);
        model.addAttribute("guess", guess);
        model.addAttribute("dice", dice);
        return "diceGameResults";
    }

}
