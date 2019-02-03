package com.oracle.bowling.game;

import com.oracle.bowling.model.Ball;
import com.oracle.bowling.model.BowlingFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.oracle.bowling.constant.BowlingGameConstant.*;

/**
 * This class implements logic for score calculation.
 *
 * @author Tejamurthy
 */
class BowlingScoreCalculator {

//    private static final Logger LOGGER = Logger.getLogger(BowlingScoreCalculator.class.getName());

    private final List<BowlingFrame> bowlingFrames;
    private int oldFrameScore = 0;

    BowlingScoreCalculator() {
        bowlingFrames = new ArrayList<>(MAX_FRAMES);
    }

    /**
     * Creates Bowling Frame and a Frame Ball for every
     * incoming ball. It creates bowling frames list and
     * calculates score for each frame
     * based on 'STRIKE', 'MISS' and 'SPARE' strategy.
     *
     * @param frameNumber, frame number
     * @param ballNumber,  ball number
     * @param pinsDown,    pins down
     */
    void calculateFrameScore(
            final int frameNumber,
            final int ballNumber,
            final int pinsDown) {
        final Ball ball = new Ball(ballNumber, pinsDown);
        if (ballNumber == BALL_1) {
            bowlingFrames.add(new BowlingFrame(frameNumber, ball));
            updatePreviousFrame(frameNumber, pinsDown, ballNumber);
            updatePreviousStrikeFrames(frameNumber, pinsDown);
        } else {
            final BowlingFrame currentFrame = bowlingFrames.get(frameNumber - 1);
            currentFrame.addBallToFrame(ball);
            final int currentFrameScore = currentFrame.getTotalPinsDown();
            updatePreviousFrame(frameNumber, currentFrameScore, ballNumber);
            if (currentFrameScore < MAX_PINS
                    && frameNumber < LAST_FRAME) {
                oldFrameScore += currentFrameScore;
                currentFrame.setFrameScore(oldFrameScore);
            } else {
                ball.setSpare(true);
            }
            if (frameNumber == LAST_FRAME) {
                currentFrame.setFrameScore(oldFrameScore + currentFrameScore);
            }
        }
    }

    /**
     * For every first and second ball, this method checks
     * whether the previous frame, if exists, is
     * a 'SPARE FRAME' or a 'STRIKE FRAME' and
     * calculates that frame score accordingly.
     *
     * @param currentFrameNumber, frame number of current frame
     * @param pinsDown,           pins down for current ball
     * @param currentBallNumber,  current ball number
     */
    private void updatePreviousFrame(
            final int currentFrameNumber,
            final int pinsDown,
            final int currentBallNumber) {
        if (currentFrameNumber > 1) {
            final BowlingFrame previousFrame = bowlingFrames.get(currentFrameNumber - 2);
            final int totalPinsDown = previousFrame.getTotalPinsDown();
            final boolean isSpare = previousFrame.getTotalBalls() == MAX_FRAME_BALLS
                    && totalPinsDown == MAX_PINS;
            final boolean isStrike = previousFrame.getPinsForBall(BALL_1) == MAX_PINS;
            if (((currentBallNumber == 1 && isSpare)
                    || currentBallNumber == BALL_2 && isStrike)
                    && previousFrame.isNotDone()) {
                oldFrameScore += MAX_PINS + pinsDown;
                previousFrame.setFrameScore(oldFrameScore);
//                LOGGER.info("Updating Score for Frame:" + currentFrameNumber);
            }
        }
    }

    /**
     * For every first ball, this method checks
     * previous two frames , if exists, are 'STRIKE FRAMES'
     * and calculates second previous frame score accordingly.
     *
     * @param currentFrameNumber, frame number of current frame
     * @param currentBallPins,    pinsDown for current ball
     */
    private void updatePreviousStrikeFrames(
            final int currentFrameNumber,
            final int currentBallPins) {
        if (currentFrameNumber > 1) {
            final BowlingFrame previousFrame = bowlingFrames.get(currentFrameNumber - 2);
            if (previousFrame.getPinsForBall(BALL_1) == MAX_PINS
                    && currentFrameNumber > 2) {
                final BowlingFrame secondPreviousFrame = bowlingFrames.get(currentFrameNumber - 3);
                if (secondPreviousFrame.getPinsForBall(BALL_1) == MAX_PINS
                        && secondPreviousFrame.isNotDone()) {
                    oldFrameScore += MAX_PINS + MAX_PINS + currentBallPins;
                    secondPreviousFrame.setFrameScore(oldFrameScore);
//                    LOGGER.info("Updating Score for Frame:" + currentFrameNumber);
                }
            }
        }
    }

    /**
     * @param frameNumber, frame number
     * @return Bowling Frame and its details for given frame number
     */
    BowlingFrame getFrame(int frameNumber) {
        return bowlingFrames.get(frameNumber - 1);
    }

}