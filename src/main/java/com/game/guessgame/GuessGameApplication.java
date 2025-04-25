package com.game.guessgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@SpringBootApplication
@Controller
public class GuessGameApplication {
    private int numbertoGuess;
    private String message;
    public GuessGameApplication(){
        Random rand = new Random();
        numbertoGuess = rand.nextInt(100)+1;
        message = "Welcome to Guess Game! Guess a number between 1 and 100";
    }
    public static void main(String[] args) {
        SpringApplication.run(GuessGameApplication.class, args);
    }

    @GetMapping("/")
    public String showGame(Model model){
        model.addAttribute("message", message);
        return "game";
    }

    @PostMapping("/guess")
    public String makeGuess(@RequestParam("guess") int userGuess, Model model){
        if(userGuess > numbertoGuess) message = "Too high, Try again";
        else if(userGuess < numbertoGuess) message = "Too low, Try again";
        else {
            message = "Congratulations! You guessed " + userGuess + " correctly";
            Random rand = new Random();
            numbertoGuess = rand.nextInt(100) + 1;
        }
        model.addAttribute("message", message);
        return  "game";
    }
}
