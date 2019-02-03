package com.oracle.bowling.game;

import com.oracle.bowling.model.Ball;
import com.oracle.bowling.model.Frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreCalculator {

    private final List<Frame> frames;
    private int previousScore = 0;

    public ScoreCalculator() {
        frames = new ArrayList<>(10);
    }

    public Frame calculateFrameScore(int frameNumber, int ballNumber, int pinsDown) {
        Ball ball = new Ball(ballNumber, pinsDown);
        if (ballNumber == 1) {
            frames.add(new Frame(frameNumber, ball));
            isPreviousFrameStrikeOrSpare(frameNumber, pinsDown, ballNumber);
            checkPreviousFramesForStrike(frameNumber, pinsDown);
        } else {
            Frame currentFrame = frames.get(frameNumber - 1);
            currentFrame.addBallToFrame(ball);
            int currentFrameScore = Arrays.stream(currentFrame.getBallScores()).sum();
            isPreviousFrameStrikeOrSpare(frameNumber, currentFrameScore, ballNumber);
            if(currentFrameScore<10 && frameNumber<10){
                previousScore +=currentFrameScore;
                currentFrame.setScore(previousScore, true);
            }
            if(frameNumber==10){
                currentFrame.setScore(previousScore+currentFrameScore,true);
            }
        }
        return frames.get(frameNumber - 1);
    }

    private void isPreviousFrameStrikeOrSpare(int currentFrameNumber, int currentBallScore, int ballNumber) {
        if (currentFrameNumber > 1) {
            Frame previousFrame = frames.get(currentFrameNumber - 2);
            int[] totalBalls = previousFrame.getBallScores();
            boolean isSpare=totalBalls.length==2 && Arrays.stream(totalBalls).sum() == 10;
            boolean isStrike=previousFrame.getBallScore(0) == 10;
            if(((ballNumber==1 && isSpare) || ballNumber==2 && isStrike) && !previousFrame.isComplete()){
                    previousScore += 10 + currentBallScore;
                    previousFrame.setScore(previousScore, true);
            }
        }
    }

    private void checkPreviousFramesForStrike(int currentFrameNumber, int currentBallScore) {
        if (currentFrameNumber > 1) {
            Frame previousFrame = frames.get(currentFrameNumber - 2);
            if(previousFrame.getBallScore(0)==10 && currentFrameNumber > 2){
                Frame previousFrame2 = frames.get(currentFrameNumber - 3);
                if(previousFrame2.getBallScore(0)==10 && !previousFrame2.isComplete()){
                    previousScore+=10+10+currentBallScore;
                    previousFrame2.setScore(previousScore, true);
                }
            }
        }
    }

}
