package Model;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Static class to process the score of a game and to save the high score.
 */
class HighScoreHandler {

    /**
     * Saves the round as a new high score if no high score file exist (first time playing the game) or if the current
     * high score was beaten.
     *
     * @param round     the round of the ended game.
     * @param gameName  the name of the game.
     */
    static void processScore(int round, String gameName) {
        try {
            if (new File(getHighScoreFolderPath(gameName)).mkdir()) {
                System.out.println(getHighScoreFolderPath(gameName) + " was created");
                saveNewHighScore(round, gameName);
            }
            else if (wasHighScoreBeaten(round, gameName)) {
                saveNewHighScore(round, gameName);
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * The new HighScore is saved in C:\Users\%UserProfile%\Documents\%GameName%\high score.txt.
     */
    private static void saveNewHighScore(int round, String gameName) throws IOException {
        System.out.println("NEW HIGH SCORE! " + round + "!");
        Writer writer = new OutputStreamWriter(new FileOutputStream(getHighScoreFile(gameName)), StandardCharsets.UTF_8);
        writer.write(String.valueOf(round));
        writer.close();
    }

    /**
     * Returns the current high score.
     *
     * @return the current high score.
     */
    private static int getHighScore(String gameName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(getHighScoreFile(gameName)), StandardCharsets.UTF_8));
        int highScore = Integer.parseInt(bufferedReader.readLine());
        bufferedReader.close();
        return highScore;
    }

    /**
     * Returns true if the score of this round is higher than the high score, otherwise false.
     *
     * @return true if the score of this round is higher than the high score, otherwise false.
     */
    private static boolean wasHighScoreBeaten(int round, String gameName) {
        try {
            return round > getHighScore(gameName);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return true;
        }
    }

    /**
     * Returns the File C:\Users\%UserProfile%\Documents\%GameName%\high score.txt.
     *
     * @return the file C:\Users\%UserProfile%\Documents\%GameName%\high score.txt.
     */
    private static File getHighScoreFile(String gameName) {
        return new File(getHighScoreFolderPath(gameName) + "\\high score.txt");
    }

    /**
     * Returns "C:\Users\%UserProfile%\Documents\%GameName%"
     *
     * @return "C:\Users\%UserProfile%\Documents\%GameName%".
     */
    private static String getHighScoreFolderPath(String gameName) {
        return System.getProperty("user.home") + "\\Documents\\" + gameName;
    }
}