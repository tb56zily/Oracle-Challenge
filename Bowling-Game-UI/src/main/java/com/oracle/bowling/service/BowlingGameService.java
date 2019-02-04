package com.oracle.bowling.service;

public interface BowlingGameService {


    void roll(int pinsDown);

    int[] getScore();
}
