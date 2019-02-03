package com.oracle.bowling.model;

public class Ball {

    private int ballNumber;

    private int pinsDown;

    public Ball(int ballNumber, int pinsDown) {
        this.ballNumber = ballNumber;
        this.pinsDown = pinsDown;
    }

    public int getBallNumber() {
        return ballNumber;
    }

    public int getPinsDown() {
        return pinsDown;
    }

    @Override
    public String toString() {
        return "Ball " + ballNumber +
                " : " + pinsDown ;
    }
}
