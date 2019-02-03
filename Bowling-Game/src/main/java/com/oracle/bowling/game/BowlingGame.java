package com.oracle.bowling.game;

/**
 * Bowling Game operations
 *
 * @author Tejamurthy
 */
public interface BowlingGame {

    /**
     * Receives pins down for a ball
     *
     * @param pinsDown, pins down for a ball
     */
    void roll(int pinsDown);

    /**
     * saves current state of game
     */
    void save();

    /**
     * Retrieves frame score for given frame number
     *
     * @param frameNumber, frame number
     * @return frame score for the frame number
     */
    int getFrameScore(int frameNumber);

    /**
     * Retrieves frame score for all completed frames
     *
     * @return frames score for all frames
     */
    int[] getAllFrameScore();

    /**
     * Retrieves total score of a Bowling Game
     *
     * @return total score of a game
     */
    int getTotalScore();

    /**
     * Retrieves details of a Game including Player name,
     * List of frames and their score,
     * along with total score of a Bowling Game
     *
     * @return Details of a Game in the form of scoreboard
     */
    String displayScoreBoard();

}