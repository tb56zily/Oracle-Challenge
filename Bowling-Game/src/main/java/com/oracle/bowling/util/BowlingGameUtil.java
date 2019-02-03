package com.oracle.bowling.util;

import com.oracle.bowling.model.BowlingGameScoreboard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BowlingGameUtil {

    public static void saveToFile(BowlingGameScoreboard scoreboard) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(scoreboard.getPlayerName() + ".txt"));
        writer.write(scoreboard.toString());
        writer.close();
    }
}
