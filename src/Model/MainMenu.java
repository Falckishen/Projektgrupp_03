package Model;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import Utilities.ViewObserver;

/**
 * Represents the main menu. The first object created.
 *
 * @author Samuel Falck
 */
public class MainMenu {

    private final int worldMapRadius;
    private final List<Integer> playerInputList;
    private final List<Integer> weaponInputList;
    private final OutputHandler outputHandler;
    private final HighScoreHandler highScoreHandler;
    private int difficulty;
    private Game currentGame;

    /**
     * Creates an instance of MainMenu.
     *
     * @param worldMapRadius the radius of the world map.
     */
    public MainMenu(String gameName, int worldMapRadius) {
        this.worldMapRadius = worldMapRadius;
        this.playerInputList = new ArrayList<>();
        this.weaponInputList = new ArrayList<>();
        this.outputHandler = new OutputHandler();
        this.highScoreHandler = new HighScoreHandler(gameName);
        this.difficulty = 1;
    }

    /*------------------------------------------------ Public Getters ------------------------------------------------*/

    /**
     * Returns the list of current user keyboard input.
     *
     * @return list of current user keyboard input.
     */
    public List<Integer> getPlayerInputList() {
        return playerInputList;
    }

    /**
     * Returns the list of current user keyboard input.
     *
     * @return list of current user keyboard input.
     */
    public List<Integer> getWeaponInputList() {
        return weaponInputList;
    }

    /**
     * Returns the current running game.
     *
     * @return the current running game.
     */
    public Game getCurrentGame() {
        return currentGame;
    }

    /**
     * Returns the high score.
     *
     * @return the high score.
     * @throws IOException if high score could not be read.
     */
    public int getHighScore() throws IOException {
        return highScoreHandler.getHighScore();
    }

    /*------------------------------------------------ Public Setters ------------------------------------------------*/

    /**
     * Sets a new difficulty.
     *
     * @param difficulty the new difficulty.
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * The main menu appears.
     */
    public void showMainMenu() {
        outputHandler.showMainMenu();
    }

    /**
     * Starts a game.
     */
    public void startGame() {
        currentGame = new Game(this, worldMapRadius, difficulty, playerInputList, weaponInputList, outputHandler);
        System.out.println("Game created");
    }

    /**
     * The score of the last game is saved if it is a new high score and the game over screen appears. Used by Game.
     */
    void gameEnded(int round) {
        highScoreHandler.processScore(round);
        outputHandler.showGameOverScreen();
    }

    /**
     * The application shuts down.
     */
    public void quitApplication() {
        System.exit(0);
    }

    /*------------------------------------------------- View Methods -------------------------------------------------*/

    /**
     * Adds a new view as an observer of the model.
     *
     * @param viewObserver the new observer of the model.
     */
    public void addViewObserver(ViewObserver viewObserver) {
        outputHandler.addViewObserver(viewObserver);
    }
}