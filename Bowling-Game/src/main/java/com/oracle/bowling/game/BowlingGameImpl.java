package com.oracle.bowling.game;

import com.oracle.bowling.exception.InvalidPinsException;
import com.oracle.bowling.model.BowlingFrame;
import com.oracle.bowling.model.BowlingGameScoreboard;
import com.oracle.bowling.util.BowlingGameUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

import static com.oracle.bowling.constant.BowlingGameConstant.*;


/**
 * This class implements Bowling Game operations.
 * Provides API implementation for rolling a ball,
 * retrieving frame scores, scoreboard and others.
 *
 * @author Tejamurthy
 */
public class BowlingGameImpl implements BowlingGame {

    private static final Logger LOGGER = Logger.getLogger(BowlingGameImpl.class);

    private int frameNumber = 1;
    private int ballNumber = 1;
    private int totalBallCount = 1;
    private BowlingScoreCalculator scoreCalculator;
    private BowlingGameScoreboard scoreboard;

    public BowlingGameImpl(String playerName) {
        this.scoreCalculator = new BowlingScoreCalculator();
        this.scoreboard = new BowlingGameScoreboard(playerName, new ArrayList<>(MAX_FRAMES));
    }

    /**
     * Receives number of pins down for a ball,
     * calculates frame score.
     *
     * @param pinsDown, pins down for a ball
     */
    public void roll(final int pinsDown) {
        LOGGER.info("Ball rolled, Frame: " + frameNumber + ", Ball:" + ballNumber);
        if (validatePins(pinsDown) && ballNumber > 0) {
            final boolean lastFrame = frameNumber == LAST_FRAME;
            scoreCalculator.calculateFrameScore(frameNumber, ballNumber, pinsDown);
            final BowlingFrame bowlingFrame = scoreCalculator.getFrame(frameNumber);
            ballNumber = ballNumber == BALL_1 ?
                    updateAfterFirstBall(pinsDown, bowlingFrame, lastFrame)
                    : updateAfterSecondBall(bowlingFrame, lastFrame);
            totalBallCount++;
        } else {
            LOGGER.error("Invalid Number of Pins or Negative Pins entered");
            throw new InvalidPinsException("Invalid Number of Pins or Negative Pins entered. Exiting Game!!!");
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
            BowlingFrame lastFrame = scoreboard.getFrame(frameNumber - 1);
            if (lastFrame != null
                    && lastFrame.getTotalPinsDown() < MAX_PINS
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
     * @return next ball number(1 or 2)
     */
    private int updateAfterFirstBall(
            final int pinsDown,
            final BowlingFrame bowlingFrame,
            final boolean lastFrame) {

        final boolean isStrike = pinsDown == MAX_PINS;
        if (isStrike) {
            scoreboard.addFrame(bowlingFrame);
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
     * @return next ball number(1, 2, 3)
     */
    private int updateAfterSecondBall(final BowlingFrame bowlingFrame, final boolean lastFrame) {
        final int ball1Score = bowlingFrame.getPinsForBall(BALL_1);
        final boolean isStrike = ball1Score == MAX_PINS;
        if (ballNumber < 3) {
            if (!lastFrame) {
                ballNumber = 1;
                frameNumber++;
            } else {
                ballNumber++;
            }
            if (!isStrike) {
                scoreboard.addFrame(bowlingFrame);
            }
        } else {
            ballNumber = -1;
        }
        return ballNumber;
    }

    /**
     * Saves current state of Game into a file
     */
    @Override
    public void save() {
        try {
            BowlingGameUtil.saveToFile(scoreboard);
        } catch (IOException e) {
            LOGGER.error("Exception while saving Game," + e);
        }
    }

    /**
     * @param frameNumber, frame number
     * @return frame score for the input frame number
     */
    @Override
    public int getFrameScore(final int frameNumber) {
        return scoreboard.getFrameScore(frameNumber);
    }

    /**
     * @return score of all 'completed' bowling frames
     */
    @Override
    public int[] getAllFrameScore() {
        return scoreboard.getAllFrameScore();
    }

    /**
     * @return score for current frame
     */
    @Override
    public int getCurrentFrameScore() {
        return scoreboard.getFrameScore(frameNumber);
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