package com.codeup.springblogapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RollDiceController {

    // Get for game page
    @GetMapping("/roll-dice")
    public String startGame(){
        return "games/diceGame";
    }

    // Post for game page
    @PostMapping("/roll-dice")
    // take guess value from main game page and store as guess
    public String resultGame(@RequestParam int guess, Model model){
        // Display message variable
        String message;

        // Random number for dice roll
        int dice = (int) Math.floor((Math.random() * 6) + 1);

        // Determine what message to send
        if (guess == dice){
            message = "Great guess!";
        } else {
            message = "Aw you didn't get it right but neither would I.";
        }

        // Store all items in model to send to results page
        model.addAttribute("message",message);
        model.addAttribute("guess", guess);
        model.addAttribute("dice", dice);

        // Go to result page
        return "games/diceGameResults";
    }

}
