package com.oracle.bowling.controller;

import com.oracle.bowling.model.ScoreResponse;
import com.oracle.bowling.service.BowlingGameService;
import com.oracle.bowling.service.BowlingGameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BowlingGameController {

    private BowlingGameService bowlingGameService;

    @Autowired
    public void setBowlingGameService(BowlingGameServiceImpl bowlingGameService) {
        this.bowlingGameService = bowlingGameService;
    }

    @PostMapping("/game/score")
    public ResponseEntity getFrameScore(@Valid @RequestBody int pinsDown, Errors errors) {
        ScoreResponse scoreResponse = new ScoreResponse();
        if (errors.hasErrors()) {
            scoreResponse.setMessage("Invalid Input");
        }
        bowlingGameService.roll(pinsDown);
        scoreResponse.setFrameScore(bowlingGameService.getScore());
        return ResponseEntity.ok(scoreResponse);
    }

}
