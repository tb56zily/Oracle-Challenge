package com.oracle.bowling.model;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private int frameNumber;

    private int frameScore;

    private boolean complete;

    private List<Ball> balls;

    public Frame(int frameNumber, Ball ball) {
        this.frameNumber = frameNumber;
        this.balls = new ArrayList<>(2);
        addBallToFrame(ball);
    }

    public void addBallToFrame(Ball ball) {
        balls.add(ball);
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public int getBallScore(int ballNumber) {
        return balls.size()>ballNumber?balls.get(ballNumber).getPinsDown():0;
    }

    public int[] getBallScores() {
        return balls.stream().mapToInt(Ball::getPinsDown).toArray();
    }

    public boolean isComplete() {
        return complete;
    }

    @Override
    public String toString() {
        return "["+"Frame:" + frameNumber +
                " "+balls +
                ", Frame Score:" + frameScore +"]";
    }

    public void setScore(int score, boolean completeFrame) {
        this.frameScore=score;
        this.complete=completeFrame;
    }

}
