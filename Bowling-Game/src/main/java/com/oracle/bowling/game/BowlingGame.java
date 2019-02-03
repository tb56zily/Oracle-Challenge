package com.oracle.bowling.game;

import com.oracle.bowling.model.ScoreBoard;

public interface BowlingGame {

    void roll(int pinsDown);

    void save();

    int getFrameScore(int frameNumber);

    int[] getFramesScore();

    int getTotalScore();

    ScoreBoard retrieveScoreBoard();

}
