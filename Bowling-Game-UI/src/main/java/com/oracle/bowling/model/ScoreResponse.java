package com.oracle.bowling.model;

public class ScoreResponse {
    private int[] frameScore;
    private String message;

    public ScoreResponse() {
    }

    public int[] getFrameScore() {
        return frameScore;
    }

    public void setFrameScore(int[] frameScore) {
        this.frameScore = frameScore;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
