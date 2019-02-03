package com.oracle.bowling.test;

import com.oracle.bowling.game.BowlingGame;
import com.oracle.bowling.game.BowlingGameImpl;
import com.oracle.bowling.model.ScoreBoard;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BowlingGameTest {

    @Test
    public void TestAllAPIs() {
        int[] pinsPerBall = {1, 0, 1, 5, 0, 8, 1, 8, 0, 1, 8, 1, 1, 0, 1, 2, 4, 5, 5, 4};
        BowlingGame game = new BowlingGameImpl("Player 1");
        IntStream.range(0, 7).forEach(i->game.roll(pinsPerBall[i]));
        ScoreBoard scoreBoard = game.retrieveScoreBoard();
        System.out.println(scoreBoard.toString());
        System.out.println("Frame 4 Score:" +game.getFrameScore(4));
        System.out.println("Total Score:" +game.getTotalScore());
        System.out.println("All Frames Score:" + Arrays.toString(game.getFramesScore()));
        System.out.println("Total Score from Score Board:" +scoreBoard.getTotalGameScore());
        System.out.println("Frame details from Score Board:" +scoreBoard.getFrameList());
    }

    @Test
    public void TestNoStrikeNoSpare() {
        int[] pinsPerBall = {1, 0, 1, 5, 0, 8, 1, 8, 0, 1, 8, 1, 1, 0, 1, 2, 4, 5, 5, 4};
        BowlingGame game = new BowlingGameImpl("Player 1");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getFramesScore();
        Assert.assertEquals(1, allFramesScore[0]);
        Assert.assertEquals( 7, allFramesScore[1]);
        Assert.assertEquals(  15, allFramesScore[2]);
        Assert.assertEquals(  24, allFramesScore[3]);
        Assert.assertEquals(  25, allFramesScore[4]);
        Assert.assertEquals( 34, allFramesScore[5]);
        Assert.assertEquals(  35, allFramesScore[6]);
        Assert.assertEquals(  38, allFramesScore[7]);
        Assert.assertEquals(  47, allFramesScore[8]);
        Assert.assertEquals( 56, allFramesScore[9]);
        validateTotalScore("NoStrikeNoSpare", allFramesScore, 56);
    }

    @Test
    public void TestAllStrike() {
        int[] pinsPerBall = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        BowlingGame game = new BowlingGameImpl("Player 1");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getFramesScore();
        Assert.assertEquals(30, allFramesScore[0]);
        Assert.assertEquals( 60, allFramesScore[1]);
        Assert.assertEquals(  90, allFramesScore[2]);
        Assert.assertEquals(  120, allFramesScore[3]);
        Assert.assertEquals(  150, allFramesScore[4]);
        Assert.assertEquals( 180, allFramesScore[5]);
        Assert.assertEquals(  210, allFramesScore[6]);
        Assert.assertEquals(  240, allFramesScore[7]);
        Assert.assertEquals(  270, allFramesScore[8]);
        Assert.assertEquals( 300, allFramesScore[9]);
        validateTotalScore("AllStrikes", allFramesScore, 300);
    }

    @Test
    public void TestMissStrikeSpare() {
        int[] pinsPerBall= {1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6};
        BowlingGame game = new BowlingGameImpl("Player 1");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getFramesScore();
        Assert.assertEquals(5, allFramesScore[0]);
        Assert.assertEquals( 14, allFramesScore[1]);
        Assert.assertEquals(  29, allFramesScore[2]);
        Assert.assertEquals(  49, allFramesScore[3]);
        Assert.assertEquals(  60, allFramesScore[4]);
        Assert.assertEquals( 61, allFramesScore[5]);
        Assert.assertEquals(  77, allFramesScore[6]);
        Assert.assertEquals(  97, allFramesScore[7]);
        Assert.assertEquals(  117, allFramesScore[8]);
        Assert.assertEquals( 133, allFramesScore[9]);
        validateTotalScore("MissStrikeSpare", allFramesScore, 133);
    }

    @Test
    public void TestOnlyLastFrameStrike() {
        int[] pinsPerBall= {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 10};
        BowlingGame game = new BowlingGameImpl("Player 1");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getFramesScore();
        Assert.assertEquals(0, allFramesScore[0]);
        Assert.assertEquals( 0, allFramesScore[1]);
        Assert.assertEquals(  0, allFramesScore[2]);
        Assert.assertEquals(  0, allFramesScore[3]);
        Assert.assertEquals(  0, allFramesScore[4]);
        Assert.assertEquals( 0, allFramesScore[5]);
        Assert.assertEquals(  0, allFramesScore[6]);
        Assert.assertEquals(  0, allFramesScore[7]);
        Assert.assertEquals(  0, allFramesScore[8]);
        Assert.assertEquals( 20, allFramesScore[9]);
        validateTotalScore("OnlyLastFrameStrike", allFramesScore, 20);
    }

    @Test
    public void TestAlternateStrike() {
        int[] pinsPerBall= {10, 3, 1, 10, 0, 6, 10, 0, 0, 10, 5, 2, 10, 0, 10, 5};
        BowlingGame game = new BowlingGameImpl("Player 1");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getFramesScore();
        Assert.assertEquals(14, allFramesScore[0]);
        Assert.assertEquals( 18, allFramesScore[1]);
        Assert.assertEquals(  34, allFramesScore[2]);
        Assert.assertEquals(  40, allFramesScore[3]);
        Assert.assertEquals(  50, allFramesScore[4]);
        Assert.assertEquals( 50, allFramesScore[5]);
        Assert.assertEquals(  67, allFramesScore[6]);
        Assert.assertEquals(  74, allFramesScore[7]);
        Assert.assertEquals(  94, allFramesScore[8]);
        Assert.assertEquals( 109, allFramesScore[9]);
        validateTotalScore("AlternateStrike", allFramesScore, 109);
    }

    @Test
    public void TestAllSpare() {
        int[] pinsPerBall= {1, 9, 2, 8, 3, 7, 4, 6, 5, 5, 6, 4, 7, 3, 0, 10, 8, 2, 9, 1, 6};
        BowlingGame game = new BowlingGameImpl("Player 1");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getFramesScore();
        Assert.assertEquals(12, allFramesScore[0]);
        Assert.assertEquals( 25, allFramesScore[1]);
        Assert.assertEquals(  39, allFramesScore[2]);
        Assert.assertEquals(  54, allFramesScore[3]);
        Assert.assertEquals(  70, allFramesScore[4]);
        Assert.assertEquals( 87, allFramesScore[5]);
        Assert.assertEquals(  97, allFramesScore[6]);
        Assert.assertEquals(  115, allFramesScore[7]);
        Assert.assertEquals(  134, allFramesScore[8]);
        Assert.assertEquals( 150, allFramesScore[9]);
        validateTotalScore("AllSpare", allFramesScore, 150);
    }

    @Test
    public void TestRandomInput() {
        int[] pinsPerBall= {10, 0, 10, 10, 5, 0, 8, 2, 9, 0, 10, 8, 1, 1, 0, 4, 6, 10};
        BowlingGame game = new BowlingGameImpl("Player 1");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getFramesScore();
        Assert.assertEquals(20, allFramesScore[0]);
        Assert.assertEquals( 40, allFramesScore[1]);
        Assert.assertEquals(  55, allFramesScore[2]);
        Assert.assertEquals(  60, allFramesScore[3]);
        Assert.assertEquals(  79, allFramesScore[4]);
        Assert.assertEquals( 88, allFramesScore[5]);
        Assert.assertEquals(  107, allFramesScore[6]);
        Assert.assertEquals(  116, allFramesScore[7]);
        Assert.assertEquals(  117, allFramesScore[8]);
        Assert.assertEquals( 137, allFramesScore[9]);
        validateTotalScore("RandomInput", allFramesScore, 137);
    }

    @Test
    public void TestAnotherInput() {
        int[] pinsPerBall= {6, 2, 7, 1, 10, 9, 0, 8, 2, 10, 10, 3, 5, 7, 2, 5, 5, 8};
        BowlingGame game = new BowlingGameImpl("Player 1");
        Arrays.stream(pinsPerBall).forEach(game::roll);
        int[] allFramesScore = game.getFramesScore();
        Assert.assertEquals(8, allFramesScore[0]);
        Assert.assertEquals( 16, allFramesScore[1]);
        Assert.assertEquals(  35, allFramesScore[2]);
        Assert.assertEquals(  44, allFramesScore[3]);
        Assert.assertEquals(  64, allFramesScore[4]);
        Assert.assertEquals( 87, allFramesScore[5]);
        Assert.assertEquals(  105, allFramesScore[6]);
        Assert.assertEquals(  113, allFramesScore[7]);
        Assert.assertEquals(  122, allFramesScore[8]);
        Assert.assertEquals( 140, allFramesScore[9]);
        validateTotalScore("AnotherInput", allFramesScore, 140);
    }

    private void validateTotalScore(String testCase, int[] frameScores, int expectedTotalScore) {
        int actualTotalScore = IntStream.range(0, frameScores.length)
                .map(i -> frameScores[9])
                .findAny()
                .orElse(-1);
        Assert.assertEquals(expectedTotalScore, actualTotalScore);
        System.out.println("==============================");
        System.out.println("TestCase::" + testCase);
        System.out.println("==============================");
        System.out.println("Frame No.  ->  " + " Frame Score");
        IntStream.range(0, frameScores.length)
                .forEach(frameNumber -> System.out.println("Frame " + (frameNumber + 1) + "    ->   " + frameScores[frameNumber]));
    }
}
