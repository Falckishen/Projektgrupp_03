package Model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the main menu, the top-level class in the Model. The first object created.
 *
 * @author Samuel Falck
 */
public class MainMenu {

    private final int worldMapRadius;
    private final List<Integer> movementInputList;
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
        this.movementInputList = new ArrayList<>();
        this.weaponInputList = new ArrayList<>();
        this.outputHandler = new OutputHandler();
        this.highScoreHandler = new HighScoreHandler(gameName);
        this.difficulty = 1;
    }

    /*------------------------------------------------ Public Getters ------------------------------------------------*/

    /**
     * Returns the list of current user keyboard input for player movement.
     *
     * @return list of current user keyboard input for player movement.
     */
    public List<Integer> getMovementInputList() {
        return movementInputList;
    }

    /**
     * Returns the list of current user keyboard input for the weapon.
     *
     * @return list of current user keyboard input for the weapon.
     */
    public List<Integer> getWeaponInputList() {
        return weaponInputList;
    }

    /**
     * Returns the current game.
     *
     * @return the current game.
     */
    public Game getCurrentGame() {
        return currentGame;
    }

    /**
     * Returns the high score.
     *
     * @return the high score.
     */
    public int getHighScore() {
        try {
            return highScoreHandler.getHighScore();
        }
        catch (Exception e) {
            return 0;
        }
    }

    public int getDifficulty(){
        try {
            return difficulty;
        }
        catch (Exception e) {
            return 0;
        }
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
     * The application shuts down.
     */
    public void quitApplication() {
        System.exit(0);
    }

    /**
     * Starts a game.
     */
    public void startGame() {
        currentGame = new Game(this, worldMapRadius, difficulty, movementInputList, weaponInputList, outputHandler);
        System.out.println("Game created");
    }

    /**
     * The score of the last game is saved if it is a new high score and the game over screen appears. Used by Game.
     */
    void gameEnded(int round) {
        highScoreHandler.processScore(round);
        outputHandler.showGameOverScreen();
        currentGame = null;
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