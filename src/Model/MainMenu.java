package Model;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {

    private final String gameName;
    private final int worldMapRadius;
    private final List<Integer> playerInputList;
    private int difficulty;

    public MainMenu(String gameName, int worldMapRadius) {
        this.gameName = gameName;
        this.worldMapRadius = worldMapRadius;
        this.playerInputList = new ArrayList<>();
        this.difficulty = 1;
    }

    public int setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void createGame() {
        Game currentGame = new Game(gameName, worldMapRadius, difficulty, playerInputList);

    }

    private void showMainMenu() {

    }
}