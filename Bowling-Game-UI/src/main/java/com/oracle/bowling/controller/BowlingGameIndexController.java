package com.oracle.bowling.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BowlingGameIndexController {

    @GetMapping("/game")
    public String index() {
        return "bowlingGame";
    }
}
