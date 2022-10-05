package Model;

import java.io.*;
import java.nio.charset.StandardCharsets;

class HighScoreHandler {

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
     * The new HighScore is saved in C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt\high score.txt.
     */
    private static void saveNewHighScore(int round, String gameName) throws IOException {
        System.out.println("NEW HIGH SCORE! " + round + "!");
        Writer writer = new OutputStreamWriter(new FileOutputStream(getHighScoreFile(gameName)), StandardCharsets.UTF_8);
        writer.write(String.valueOf(round));
        writer.close();
    }

    /**
     * Returns the old high score.
     *
     * @return the old high score.
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
     * Returns the File C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt\high score.txt.
     *
     * @return file C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt\high score.txt.
     */
    private static File getHighScoreFile(String gameName) {
        return new File(getHighScoreFolderPath(gameName) + "\\high score.txt");
    }

    /**
     * Returns "C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt"
     *
     * @return "C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt".
     */
    private static String getHighScoreFolderPath(String gameName) {
        return System.getProperty("user.home") + "\\Documents\\" + gameName;
    }
}