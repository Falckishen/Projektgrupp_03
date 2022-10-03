package Model;

import Utilities.ViewObserver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainMenu {

    private final String gameName;
    private final int worldMapRadius;
    private final List<Integer> playerInputList;
    private List<ViewObserver> viewObservers;
    private int difficulty;

    public MainMenu(String gameName, int worldMapRadius) {
        this.gameName = gameName;
        this.worldMapRadius = worldMapRadius;
        this.playerInputList = new ArrayList<>();
        this.viewObservers = new ArrayList<>();
        this.difficulty = 1;
    }

    public void createGame() {
        Game currentGame = new Game(gameName, worldMapRadius, difficulty, playerInputList, viewObservers);

        if (new File(getHighScoreFolderPath()).mkdir()) {
            System.out.println(getHighScoreFolderPath() + " was created");
            saveNewHighScore();
        }
        else if (wasHighScoreBeaten()) {
            saveNewHighScore();
        }

        // TODO visa game-over skärm
    }

    private void showMainMenu() {

    }

    /*------------------------------------------------ Public Getters ------------------------------------------------*/

    public Game getCurrentGame() {
        return currentGame;
    }

    /**
     * Returns the list of current user keyboard input.
     *
     * @return list of current user keyboard input.
     */
    public List<Integer> getPlayerInputList() {
        return playerInputList;
    }

    /*------------------------------------------------ Public Setters ------------------------------------------------*/

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /*-------------------------------------------- ViewObservers methods --------------------------------------------*/

    /**
     * Adds a view to become an observer of the model.
     *
     * @param viewObserver the view to be added as an observer of the model.
     */
    public void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
    }

    /*------------------------------------------- saveHighScore methods -------------------------------------------*/

    // TODO flytta

    /**
     * The new HighScore is saved in C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt\high score.txt.
     */
    private void saveNewHighScore(int round) {
        System.out.println("NEW HIGH SCORE! " + round + "!");
        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream(getHighScoreFile()), StandardCharsets.UTF_8);
            writer.write(String.valueOf(round));
            writer.close();
        }
        catch (Exception exception) {
            System.out.println("Error when saving high score: " + exception.getMessage());
        }
    }

    /**
     * Returns the File C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt\high score.txt.
     *
     * @return file C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt\high score.txt.
     */
    private File getHighScoreFile() {
        return new File(getHighScoreFolderPath() + "\\high score.txt");
    }

    /**
     * Returns "C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt"
     *
     * @return "C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt".
     */
    private String getHighScoreFolderPath() {
        return System.getProperty("user.home") + "\\Documents\\" + gameName;
    }

    /**
     * Returns the old high score.
     *
     * @return the old high score.
     * @throws Exception if there is an error reading the old high score in
     * C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt\high score.txt.
     */
    public int getHighScore() throws Exception {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(getHighScoreFile()), StandardCharsets.UTF_8));
            int highScore = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
            return highScore;
        }
        catch (Exception exception) {
            throw new Exception("Error when reading old high score: " + exception.getMessage());
        }
    }

    /**
     * Returns true if the score of this round is higher than the high score, otherwise false.
     *
     * @return true if the score of this round is higher than the high score, otherwise false.
     */
    public boolean wasHighScoreBeaten() {
        try {
            return round > getHighScore();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return true;
        }
    }
}