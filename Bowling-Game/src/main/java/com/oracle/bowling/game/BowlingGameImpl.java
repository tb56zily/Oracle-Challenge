package com.oracle.bowling.game;

import com.oracle.bowling.model.BowlingFrame;
import com.oracle.bowling.model.BowlingGameScoreboard;
import com.oracle.bowling.util.BowlingGameUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.oracle.bowling.game.BowlingGameConstant.*;


/**
 * This class implements Bowling Game operations.
 * Provides API implementation for rolling a ball,
 * retrieving frame scores, scoreboard and others.
 *
 * @author Tejamurthy
 */
public class BowlingGameImpl implements BowlingGame {

    private static final Logger LOGGER = Logger.getLogger(BowlingGameImpl.class.getName());

    private int frameNumber = 1;
    private int ballNumber = 1;
    private int totalBallCount = 1;
    private BowlingScoreCalculator scoreCalculator;
    private BowlingGameScoreboard scoreboard;
    private final List<BowlingFrame> bowlingFrames;

    public BowlingGameImpl(String playerName) {
        this.scoreCalculator = new BowlingScoreCalculator();
        this.bowlingFrames = new ArrayList<>(MAX_FRAMES);
        this.scoreboard = new BowlingGameScoreboard(playerName, bowlingFrames);
    }

    /**
     * Receives number of pins down for a ball,
     * creates Bowling Frame and
     * calculates frame score.
     *
     * @param pinsDown, pins down for a ball
     */
    public void roll(final int pinsDown) {

        LOGGER.info("Ball rolled, Frame: " + frameNumber + ", Ball:" + ballNumber);
        if (validatePins(pinsDown)) {
            final boolean lastFrame = frameNumber == LAST_FRAME;
            scoreCalculator.calculateFrameScore(frameNumber, ballNumber, pinsDown);
            final BowlingFrame bowlingFrame = scoreCalculator.getFrame(frameNumber);
            ballNumber = ballNumber == BALL_1 ?
                    updateAfterFirstBall(pinsDown, bowlingFrame, lastFrame)
                    : updateAfterSecondBall(pinsDown, bowlingFrame, lastFrame);
            totalBallCount++;
        } else {
            LOGGER.info("Invalid Number of Pins or Negative Pins entered");
            throw new IllegalArgumentException("Invalid Number of Pins or Negative Pins entered. Exiting Game!!!");
        }
    }

    /**
     * Validates input pins down
     *
     * @param pinsDown, pins down for a ball
     * @return true if valid
     */
    private boolean validatePins(int pinsDown) {
        if (frameNumber == 10 && ballNumber == BONUS_BALL) {
            BowlingFrame lastFrame = bowlingFrames.get(frameNumber - 1);
            if (lastFrame.getTotalPinsDown() < MAX_PINS
                    && lastFrame.getTotalBalls() == MAX_FRAME_BALLS) {
                return false;
            }
        }
        return pinsDown >= 0 && pinsDown <= 10 && totalBallCount <= MAX_BALLS;
    }

    /**
     * After processing pins down for every first ball
     * of a frame, this method updates 'ball number(1-2)',
     * 'frame number(1-10)' and adds current bowling frame
     * to list of frames.
     *
     * @param pinsDown,     pins down for every first ball
     * @param bowlingFrame, current bowling frame
     * @param lastFrame,    true only for tenth bowling frame
     * @return next ball number(1 0r 2)
     */
    private int updateAfterFirstBall(
            final int pinsDown,
            final BowlingFrame bowlingFrame,
            final boolean lastFrame) {

        final boolean isStrike = pinsDown == MAX_PINS;
        if (isStrike) {
            bowlingFrames.add(bowlingFrame);
        }
        if (!isStrike || lastFrame) {
            ballNumber++;
        }
        if (isStrike && !lastFrame) {
            frameNumber++;
        }
        return ballNumber;
    }

    /**
     * After processing pinsDown for every second ball
     * of a bowling frame, this method updates 'ball number(1-3)',
     * 'frame number(1-10)' and adds current bowling frame
     * to list of frames.
     *
     * @param bowlingFrame, current bowling frame
     * @param lastFrame,    true only for tenth bowling frame
     * @param pinsDown
     * @return next ball number(1, 2, 3)
     */
    private int updateAfterSecondBall(int pinsDown, final BowlingFrame bowlingFrame, final boolean lastFrame) {
        final int ball1Score = bowlingFrame.getPinsForBall(BALL_1);
        final boolean isStrike = ball1Score == MAX_PINS;
        final boolean isSpare = ball1Score + pinsDown == MAX_PINS;
        if (ballNumber < 3) {
            if (!lastFrame) {
                ballNumber = 1;
                frameNumber++;
            } else {
                ballNumber++;
            }
            if (!isStrike) {
                bowlingFrames.add(bowlingFrame);
            }
        }
        return ballNumber;
    }

    /**
     * Saves current state of Game into a file
     */
    public void save() {
        try {
            BowlingGameUtil.saveToFile(scoreboard);
        } catch (IOException e) {
            LOGGER.info("Exception while saving Game," + e);
        }
    }

    /**
     * @param frameNumber, frame number
     * @return frame score for the input frame number
     */
    public int getFrameScore(final int frameNumber) {
        return scoreboard.getFrameScore(frameNumber);
    }

    /**
     * @return score of all 'completed' bowling frames
     */
    public int[] getAllFrameScore() {
        return scoreboard.getAllFrameScore();
    }

    /**
     * @return total score of a Bowling Game at that point
     */
    public int getTotalScore() {
        return scoreboard.getTotalGameScore();
    }

    @Override
    public String displayScoreBoard() {
        return scoreboard.toString();
    }
}