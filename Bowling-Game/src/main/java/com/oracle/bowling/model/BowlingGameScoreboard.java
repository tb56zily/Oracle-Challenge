package com.oracle.bowling.model;

import java.util.List;

/**
 * This class represents 'Scoreboard' of a Bowling Game.
 * It includes Player name, list of frames and
 * frame score for every frame.
 *
 * @author Tejamurthy
 */
public class BowlingGameScoreboard {

    private String playerName;

    private List<BowlingFrame> bowlingFrameList;

    public BowlingGameScoreboard(String playerName, List<BowlingFrame> bowlingFrameList) {
        this.playerName = playerName;
        this.bowlingFrameList = bowlingFrameList;
    }

    public String getPlayerName() {
        return playerName;
    }

    /**
     * @return list of all the frames of a game at that point.
     */
    public int[] getAllFrameScore() {
        return bowlingFrameList.stream().mapToInt(BowlingFrame::getFrameScore).toArray();
    }

    /**
     * @return total score of a game
     */
    public int getTotalGameScore() {
        return bowlingFrameList.get(bowlingFrameList.size() - 1).getFrameScore();
    }

    /**
     * @param frameNumber, frame number
     * @return score of a frame
     */
    public int getFrameScore(final int frameNumber) {
        return frameNumber < bowlingFrameList.size() ?
                bowlingFrameList.get(frameNumber - 1).getFrameScore()
                : 0;
    }

    @Override
    public String toString() {
        return "\nPlayer Name='" + playerName + '\'' +
                "\nTotal Score=" + getTotalGameScore() +
                "\nFrame Score=" + bowlingFrameList;
    }
}
