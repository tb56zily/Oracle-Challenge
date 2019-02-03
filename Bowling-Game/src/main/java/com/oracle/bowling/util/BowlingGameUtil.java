package com.oracle.bowling.util;

import com.oracle.bowling.model.BowlingGameScoreboard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class implements utility functions like
 * saving a game state into the text file.
 *
 * @author Tejamurthy
 */
public class BowlingGameUtil {

    /**
     * @param scoreboard, scoreboard of a game
     * @throws IOException if fails to save
     */
    public static void saveToFile(final BowlingGameScoreboard scoreboard) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(scoreboard.getPlayerName() + ".txt"));
        writer.write(scoreboard.toString());
        writer.close();
    }
}
