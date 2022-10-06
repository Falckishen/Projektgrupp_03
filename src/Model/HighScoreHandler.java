package Model;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * A class to process the score of a game and to save and get high score.
 */
class HighScoreHandler {

    private final String gameName;

    HighScoreHandler(String gameName) {
        this.gameName = gameName;
    }

    /**
     * Saves the round as a new high score if no high score file exist (first time playing the game) or if the current
     * high score was beaten.
     *
     * @param round     the round of the ended game.
     */
    void processScore(int round) {
        try {
            if (new File(getHighScoreFolderPath()).mkdir()) {
                System.out.println(getHighScoreFolderPath() + " was created");
                saveNewHighScore(round);
            }
            else if (wasHighScoreBeaten(round)) {
                saveNewHighScore(round);
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Returns the current high score.
     *
     * @return the current high score.
     */
    int getHighScore() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(getHighScoreFile()), StandardCharsets.UTF_8));
        int highScore = Integer.parseInt(bufferedReader.readLine());
        bufferedReader.close();
        return highScore;
    }

    /**
     * The new HighScore is saved in C:\Users\%UserProfile%\Documents\%GameName%\high score.txt.
     */
    private void saveNewHighScore(int round) throws IOException {
        System.out.println("NEW HIGH SCORE! " + round + "!");
        Writer writer = new OutputStreamWriter(new FileOutputStream(getHighScoreFile()), StandardCharsets.UTF_8);
        writer.write(String.valueOf(round));
        writer.close();
    }

    /**
     * Returns true if the score of this round is higher than the high score, otherwise false.
     *
     * @return true if the score of this round is higher than the high score, otherwise false.
     */
    private boolean wasHighScoreBeaten(int round) {
        try {
            return round > getHighScore();
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
    private File getHighScoreFile() {
        return new File(getHighScoreFolderPath() + "\\high score.txt");
    }

    /**
     * Returns "C:\Users\%UserProfile%\Documents\%GameName%"
     *
     * @return "C:\Users\%UserProfile%\Documents\%GameName%".
     */
    private String getHighScoreFolderPath() {
        return System.getProperty("user.home") + "\\Documents\\" + gameName;
    }
}