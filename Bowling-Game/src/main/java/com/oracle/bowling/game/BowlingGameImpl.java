package com.oracle.bowling.game;

import com.oracle.bowling.model.Frame;
import com.oracle.bowling.model.ScoreBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingGameImpl implements BowlingGame {

    private String playerName;
    private  int frameNumber=1;
    private  int ballNumber=1;
    private ScoreCalculator scoreCalculator;
    private final List<Frame> frames = new ArrayList<>(10);

    public BowlingGameImpl(String playerName) {
        this.playerName = playerName;
        this.scoreCalculator=new ScoreCalculator();
    }

    public void roll(int pinsDown) {
        boolean lastFrame=frameNumber == 10;
        final Frame frame = scoreCalculator.calculateFrameScore(frameNumber, ballNumber, pinsDown);
        ballNumber= ballNumber==1?processBall1(pinsDown, frame, lastFrame): processBall2(pinsDown, frame, lastFrame);
         /* if(ballNumber==1 ){
               processBall1(pinsDown, frame, lastFrame);
        }else{
              processBall2(pinsDown, frame, lastFrame);
        }*/
    }

    //TODO change name of proceesballs
    private int processBall1(int pinsDown, Frame frame, boolean isLastFrame) {
        boolean isStrike = pinsDown==10;
        if(isStrike){
            frames.add(frame);
        }
        if(!isStrike || isLastFrame){
            ballNumber++;
        }
        if(isStrike && !isLastFrame){
            frameNumber++;
        }
        return ballNumber;
    }

    private int processBall2(int pinsDown, Frame frame, boolean lastFrame) {
        if(ballNumber<3) {
            int ball1Score = frame.getBallScore(0);
            boolean isStrike = ball1Score == 10;
            boolean isSpare = ball1Score + pinsDown == 10;
            if (!lastFrame) {
                ballNumber = 1;
                frameNumber++;
            } else if (isSpare || isStrike) {
                ballNumber++;
            }
            if (!isStrike) {
                frames.add(frame);
            }
        }
        return ballNumber;
    }

    public void save() {
//TODO
    }

    public int getFrameScore(int frameNumber) {
        return frameNumber<frames.size()?frames.get(frameNumber-1).getFrameScore():0;
    }

    public int[] getFramesScore() {
        return frames.stream().mapToInt(Frame::getFrameScore).toArray();
    }

    public int getTotalScore() {
        return frames.get(frames.size()-1).getFrameScore();
    }

    @Override
    public ScoreBoard retrieveScoreBoard() {
        return new ScoreBoard(playerName,Collections.unmodifiableList(frames));
    }
}
