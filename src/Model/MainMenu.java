package Model;

import Utilities.ViewObserver;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {

    private final String gameName;
    private final int worldMapRadius;
    private final List<Integer> playerInputList;
    private List<ViewObserver> viewObservers;
    private int difficulty;
    private Game currentGame;

    public MainMenu(String gameName, int worldMapRadius) {
        this.gameName = gameName;
        this.worldMapRadius = worldMapRadius;
        this.playerInputList = new ArrayList<>();
        this.viewObservers = new ArrayList<>();
        this.difficulty = 1;
    }

    public void createGame() {
        currentGame = new Game(gameName, worldMapRadius, difficulty, playerInputList, viewObservers);
        int round = currentGame.getRound();
        HighScoreHandler.processScore(round, gameName);

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
}