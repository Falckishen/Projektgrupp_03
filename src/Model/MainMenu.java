package Model;

import Utilities.ViewObserver;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {

    private final String gameName;
    private final int worldMapRadius;
    private final List<Integer> playerInputList;
    private final OutputHandler outputHandler;
    private int difficulty;
    private Game currentGame;

    public MainMenu(String gameName, int worldMapRadius) {
        this.gameName = gameName;
        this.worldMapRadius = worldMapRadius;
        this.playerInputList = new ArrayList<>();
        this.outputHandler = new OutputHandler();
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

    public Game getCurrentGame() {
        return currentGame;
    }

    /*------------------------------------------------ Public Setters ------------------------------------------------*/

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    public void startGame() {
        currentGame = new Game(worldMapRadius, difficulty, playerInputList, outputHandler);
        System.out.println("Game created");
        currentGame.startGame();
        /*
        int round = currentGame.getRound();
        currentGame = null;
        HighScoreHandler.processScore(round, gameName);
        */
        // TODO visa game-over skärm
    }

    public void showMainMenu() {
        outputHandler.showMainMenu();
    }

    /*------------------------------------------------- View Methods -------------------------------------------------*/

    public void addViewObserver(ViewObserver viewObserver) {
        outputHandler.addViewObserver(viewObserver);
    }
}