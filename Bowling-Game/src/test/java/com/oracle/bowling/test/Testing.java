package com.oracle.bowling.test;

import com.oracle.bowling.game.BowlingGame;
import com.oracle.bowling.game.BowlingGameImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Testing {

    @Test
    public void TestAlternateStrikeSpare() {
        int[] pinsPerBall = {10,8,2,10,9,1,10,0,10,10,3,7,10,5,5,10};
        BowlingGame game = new BowlingGameImpl("Player 1");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getAllFrameScore();
        Assert.assertEquals(20, allFramesScore[0]);
        Assert.assertEquals(40, allFramesScore[1]);
        Assert.assertEquals(60, allFramesScore[2]);
        Assert.assertEquals(80, allFramesScore[3]);
        Assert.assertEquals(100, allFramesScore[4]);
        Assert.assertEquals(120, allFramesScore[5]);
        Assert.assertEquals(140, allFramesScore[6]);
        Assert.assertEquals(160, allFramesScore[7]);
        Assert.assertEquals(180, allFramesScore[8]);
        Assert.assertEquals(200, allFramesScore[9]);
        validateTotalScore("AlternateStrikeSpare", allFramesScore, 200);
    }


    @Test
    public void TestAlternateSpareStrike() {
        int[] pinsPerBall = {8,2,10,9,1,10,0,10,10,3,7,10,5,5,10,10,10};
        BowlingGame game = new BowlingGameImpl("Player 2");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getAllFrameScore();
        Assert.assertEquals(20, allFramesScore[0]);
        Assert.assertEquals(40, allFramesScore[1]);
        Assert.assertEquals(60, allFramesScore[2]);
        Assert.assertEquals(80, allFramesScore[3]);
        Assert.assertEquals(100, allFramesScore[4]);
        Assert.assertEquals(120, allFramesScore[5]);
        Assert.assertEquals(140, allFramesScore[6]);
        Assert.assertEquals(160, allFramesScore[7]);
        Assert.assertEquals(180, allFramesScore[8]);
        Assert.assertEquals(210, allFramesScore[9]);
        validateTotalScore("AlternateSpareStrike", allFramesScore, 210);
    }

    @Test
    public void TestAllGutter() {
        int[] pinsPerBall = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        BowlingGame game = new BowlingGameImpl("Player 2");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getAllFrameScore();
        Assert.assertEquals(0, allFramesScore[0]);
        Assert.assertEquals(0, allFramesScore[1]);
        Assert.assertEquals(0, allFramesScore[2]);
        Assert.assertEquals(0, allFramesScore[3]);
        Assert.assertEquals(0, allFramesScore[4]);
        Assert.assertEquals(0, allFramesScore[5]);
        Assert.assertEquals(0, allFramesScore[6]);
        Assert.assertEquals(0, allFramesScore[7]);
        Assert.assertEquals(0, allFramesScore[8]);
        Assert.assertEquals(0, allFramesScore[9]);
        validateTotalScore("AllGutter", allFramesScore, 0);
    }

    @Test
    public void TestStrikeGutter() {
        int[] pinsPerBall = {0,0,0,0,10,10,10,0,0,0,0,0,0,0,0,0,0};
        BowlingGame game = new BowlingGameImpl("Player 2");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getAllFrameScore();
        Assert.assertEquals(0, allFramesScore[0]);
        Assert.assertEquals(0, allFramesScore[1]);
        Assert.assertEquals(30, allFramesScore[2]);
        Assert.assertEquals(50, allFramesScore[3]);
        Assert.assertEquals(60, allFramesScore[4]);
        Assert.assertEquals(60, allFramesScore[5]);
        Assert.assertEquals(60, allFramesScore[6]);
        Assert.assertEquals(60, allFramesScore[7]);
        Assert.assertEquals(60, allFramesScore[8]);
        Assert.assertEquals(60, allFramesScore[9]);
        validateTotalScore("StrikeGutter", allFramesScore, 60);
    }

    @Test
    public void TestSpareGutter() {
        int[] pinsPerBall = {0,0,0,0,1,9,4,6,7,3,0,0,0,0,0,0,0,0,0,0};
        BowlingGame game = new BowlingGameImpl("Player 2");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getAllFrameScore();
        Assert.assertEquals(0, allFramesScore[0]);
        Assert.assertEquals(0, allFramesScore[1]);
        Assert.assertEquals(14, allFramesScore[2]);
        Assert.assertEquals(31, allFramesScore[3]);
        Assert.assertEquals(41, allFramesScore[4]);
        Assert.assertEquals(41, allFramesScore[5]);
        Assert.assertEquals(41, allFramesScore[6]);
        Assert.assertEquals(41, allFramesScore[7]);
        Assert.assertEquals(41, allFramesScore[8]);
        Assert.assertEquals(41, allFramesScore[9]);
        validateTotalScore("SpareGutter", allFramesScore, 41);
    }

    @Test
    public void TestLastStrike() {
        int[] pinsPerBall = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,10,10};
        BowlingGame game = new BowlingGameImpl("Player 2");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getAllFrameScore();
        Assert.assertEquals(0, allFramesScore[0]);
        Assert.assertEquals(0, allFramesScore[1]);
        Assert.assertEquals(0, allFramesScore[2]);
        Assert.assertEquals(0, allFramesScore[3]);
        Assert.assertEquals(0, allFramesScore[4]);
        Assert.assertEquals(0, allFramesScore[5]);
        Assert.assertEquals(0, allFramesScore[6]);
        Assert.assertEquals(0, allFramesScore[7]);
        Assert.assertEquals(0, allFramesScore[8]);
        Assert.assertEquals(30, allFramesScore[9]);
        validateTotalScore("LastStrike", allFramesScore, 30);
    }

    @Test
    public void TestAlternateStrikeGutter() {
        int[] pinsPerBall = {10,0,0,10,0,0,10,10,0,0,10,10,0,10,0};
        BowlingGame game = new BowlingGameImpl("Player 2");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getAllFrameScore();
        Assert.assertEquals(10, allFramesScore[0]);
        Assert.assertEquals(10, allFramesScore[1]);
        Assert.assertEquals(20, allFramesScore[2]);
        Assert.assertEquals(20, allFramesScore[3]);
        Assert.assertEquals(40, allFramesScore[4]);
        Assert.assertEquals(50, allFramesScore[5]);
        Assert.assertEquals(50, allFramesScore[6]);
        Assert.assertEquals(70, allFramesScore[7]);
        Assert.assertEquals(90, allFramesScore[8]);
        Assert.assertEquals(100, allFramesScore[9]);
        validateTotalScore("AlternateStrikeGutter", allFramesScore, 100);
    }

    public void TestRandom() {
        int[] pinsPerBall = {10,5,3,3,7,10,0,8,5,2,8,1,4,2,10,1,2};
        BowlingGame game = new BowlingGameImpl("Player 2");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getAllFrameScore();
        Assert.assertEquals(18, allFramesScore[0]);
        Assert.assertEquals(26, allFramesScore[1]);
        Assert.assertEquals(46, allFramesScore[2]);
        Assert.assertEquals(64, allFramesScore[3]);
        Assert.assertEquals(72, allFramesScore[4]);
        Assert.assertEquals(79, allFramesScore[5]);
        Assert.assertEquals(88, allFramesScore[6]);
        Assert.assertEquals(94, allFramesScore[7]);
        Assert.assertEquals(107, allFramesScore[8]);
        Assert.assertEquals(110, allFramesScore[9]);
        validateTotalScore("Random", allFramesScore, 110);
    }


    @Test
    public void TestRandom2() {
        int[] pinsPerBall = {10,7,2,7,2,8,1,8,2,7,3,8,2,7,0,7,3,10,9,1};
        BowlingGame game = new BowlingGameImpl("Player 2");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getAllFrameScore();
        Assert.assertEquals(19, allFramesScore[0]);
        Assert.assertEquals(28, allFramesScore[1]);
        Assert.assertEquals(37, allFramesScore[2]);
        Assert.assertEquals(46, allFramesScore[3]);
        Assert.assertEquals(63, allFramesScore[4]);
        Assert.assertEquals(81, allFramesScore[5]);
        Assert.assertEquals(98, allFramesScore[6]);
        Assert.assertEquals(105, allFramesScore[7]);
        Assert.assertEquals(125, allFramesScore[8]);
        Assert.assertEquals(145, allFramesScore[9]);
        validateTotalScore("Random2", allFramesScore, 145);
    }

    public void TestRandom3() {
        int[] pinsPerBall = {10,9,1,8,1,10,10,10,7,3,10,7,2,9,1,9};
        BowlingGame game = new BowlingGameImpl("Player 2");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getAllFrameScore();
        Assert.assertEquals(20, allFramesScore[0]);
        Assert.assertEquals(38, allFramesScore[1]);
        Assert.assertEquals(47, allFramesScore[2]);
        Assert.assertEquals(77, allFramesScore[3]);
        Assert.assertEquals(104, allFramesScore[4]);
        Assert.assertEquals(124, allFramesScore[5]);
        Assert.assertEquals(144, allFramesScore[6]);
        Assert.assertEquals(163, allFramesScore[7]);
        Assert.assertEquals(172, allFramesScore[8]);
        Assert.assertEquals(191, allFramesScore[9]);
        validateTotalScore("Random3", allFramesScore, 191);
    }

    public void TestRandom4() {
        int[] pinsPerBall = {9,1,10,9,1,10,9,1,10,10,9,1,10,10,10,10};
        BowlingGame game = new BowlingGameImpl("Player 2");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getAllFrameScore();
        Assert.assertEquals(20, allFramesScore[0]);
        Assert.assertEquals(40, allFramesScore[1]);
        Assert.assertEquals(60, allFramesScore[2]);
        Assert.assertEquals(80, allFramesScore[3]);
        Assert.assertEquals(100, allFramesScore[4]);
        Assert.assertEquals(129, allFramesScore[5]);
        Assert.assertEquals(149, allFramesScore[6]);
        Assert.assertEquals(169, allFramesScore[7]);
        Assert.assertEquals(199, allFramesScore[8]);
        Assert.assertEquals(229, allFramesScore[9]);
        validateTotalScore("Random4", allFramesScore, 229);
    }


    private void validateTotalScore(String testCase, int[] frameScores, int expectedTotalScore) {
        int framesLength = frameScores.length;
        int actualTotalScore = IntStream.range(0, framesLength)
                .map(i -> frameScores[framesLength - 1])
                .findAny()
                .orElse(-1);
        Assert.assertEquals(expectedTotalScore, actualTotalScore);
        System.out.println("\n");
        System.out.println("TestCase::" + testCase);
        System.out.println("===============================");
        System.out.println("Frame No.  ->  " + " Frame Score");
        IntStream.range(0, framesLength)
                .forEach(frameNumber -> System.out.println("Frame " + (frameNumber + 1) + "    ->   " + frameScores[frameNumber]));
        System.out.println(" ");
        System.out.println("Total Game Score:" + actualTotalScore);
        System.out.println("==============================");
    }

    private void displayScoreboard(String scoreboard) {
        System.out.println("\n");
        System.out.println("Scoreboard:" + scoreboard);
        System.out.println("LEGEND: 'X' - Strike frame , '/' - Spare Frame");
        System.out.println("------------------------------------------------");
    }
}
