package com.oracle.bowling.service;

import org.springframework.stereotype.Service;

@Service
public class BowlingGameServiceImpl implements BowlingGameService {

    public void roll(final int pinsDown) {

    }

    public int[] getScore() {
        return new int[9];
    }
}