package com.oracle.bowling.model;

import java.util.List;

public class ScoreBoard {

    private String playerName;

    private List<Frame> frameList;

    private int totalGameScore;

    public ScoreBoard(String playerName, List<Frame> frameList) {
        this.playerName = playerName;
        this.frameList = frameList;
        this.totalGameScore=frameList.get(frameList.size()-1).getFrameScore();
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Frame> getFrameList() {
        return frameList;
    }

    public int getTotalGameScore() {
        return totalGameScore;
    }

    @Override
    public String toString() {
        return "ScoreBoard{" +
                "playerName='" + playerName + '\'' +
                ", Score=" + frameList +
                ", Total Score=" + totalGameScore +
                '}';
    }
}
