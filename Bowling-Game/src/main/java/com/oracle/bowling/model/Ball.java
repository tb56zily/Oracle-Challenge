package com.oracle.bowling.model;

/**
 * This class represents Ball of a frame in Bowling Game.
 *
 * @author Tejamurthy
 */
public class Ball {

    private int ballNumber;

    private int pinsDown;

    private boolean spare;

    public Ball(int ballNumber, int pinsDown) {
        this.ballNumber = ballNumber;
        this.pinsDown = pinsDown;
    }

    /**
     * @return pins down for a ball
     */
    int getPinsDown() {
        return pinsDown;
    }

    /**
     * @return true if spare on ball 2
     */
    public boolean isSpare() {
        return spare;
    }

    /**
     * @param spare, true if spare ball
     */
    public void setSpare(boolean spare) {
        this.spare = spare;
    }

    @Override
    public String toString() {
        StringBuilder ballAsString = new StringBuilder("Ball ");
        ballAsString.append(ballNumber);
        ballAsString.append(":");
        if (ballNumber == 1 && pinsDown == 10) {
            ballAsString.append("X");
        } else if (isSpare()) {
            ballAsString.append("/");
        } else {
            ballAsString.append(pinsDown);
        }
        return ballAsString.toString();
    }
}
