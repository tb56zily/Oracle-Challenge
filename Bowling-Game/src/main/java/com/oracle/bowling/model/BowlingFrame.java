package com.oracle.bowling.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents 'BowlingFrame' of a Bowling Game.
 * It includes frame number, list of balls and
 * frame score for every frame.
 *
 * @author Tejamurthy
 */
public class BowlingFrame {

    private int frameNumber;

    private int frameScore;

    private boolean notDone;

    private List<Ball> balls;

    public BowlingFrame(int frameNumber, Ball ball) {
        this.frameNumber = frameNumber;
        this.balls = new ArrayList<>(2);
        addBallToFrame(ball);
    }

    /**
     * Adds a new ball with its pins for
     * 'this' frame.
     *
     * @param ball, ball of a frame
     */
    public void addBallToFrame(Ball ball) {
        balls.add(ball);
    }

    /**
     * @return score for 'this' frame
     */
    public int getFrameScore() {
        return frameScore;
    }

    /**
     * @param ballNumber, ball number
     * @return pins down for a given ball number
     */
    public int getPinsForBall(int ballNumber) {
        return balls.size() >= ballNumber ? balls.get(ballNumber - 1).getPinsDown() : 0;
    }

    /**
     * @return total balls in a frame
     */
    public int getTotalBalls() {
        return balls.size();
    }

    /**
     * @return total pins down for a frame
     */
    public int getTotalPinsDown() {
        return balls.stream().mapToInt(Ball::getPinsDown).sum();
    }

    /**
     * @return true if the frame is notDone, else false
     */
    public boolean isNotDone() {
        return !notDone;
    }

    /**
     * Assign score for a frame and
     * sets the field 'notDone' to corresponding value
     *
     * @param score, score of a frame
     */
    public void setFrameScore(int score) {
        this.frameScore = score;
        this.notDone = true;
    }

    @Override
    public String toString() {
        return "[" + "Frame:" + frameNumber +
                " " + balls +
                ", Frame Score:" + frameScore + "]";
    }

}
