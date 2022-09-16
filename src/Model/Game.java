package Model;

import java.util.ArrayList;
import java.util.Timer;

import Model.Entities.EntityCreator;
import Model.Entities.Monster;
import Model.Entities.Player;
import Model.Entities.Position;
import Utilities.Direction;
import Utilities.ViewObserver;

// The "main" class for Model.
// Follows the facade pattern, this should be the only class in Model to communicate with controller and view.
public class Game extends Thread {

    private final ArrayList<ViewObserver> viewObservers;
    private Player player;
    private ArrayList<Monster> monsters;
    private ArrayList<Direction> currentPlayerDirections;
    private int round;

    private final EntityCreator entityCreator;

    /*------------------------------------------------- Constructor -------------------------------------------------*/

    public Game() {
        this.entityCreator = new EntityCreator();

        this.viewObservers = new ArrayList<>();
        this.round = 0;
    }

    /*--------------------------------------------------- Getters ---------------------------------------------------*/

    public ArrayList<ViewObserver> getViewObservers() {
        return viewObservers;
    }

    public Player getPlayer() {
        return player;
    }

    public Position getPlayerPosition() {
        return player.getCurrentPosition();
    }

    ArrayList<Monster> getMonsters() {
        return monsters;
    }

    /*--------------------------------------------------- Setters ---------------------------------------------------*/

    public void setCurrentPlayerDirections(ArrayList<Direction> currentPlayerDirections) {
        this.currentPlayerDirections = currentPlayerDirections;
    }

    /*---------------------------------------- Public ViewObservers Methods ----------------------------------------*/

    public void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
    }

    /*-------------------------------------------------- Threading --------------------------------------------------*/

    // This method runs as a thread, inputs are running parallel
    public void run() {
        this.player = entityCreator.createPlayer(0,0, currentPlayerDirections);
        this.monsters = new ArrayList<>();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new WorldUpdate(this), 0, 17);
        // 1.task 2. delay 3. period
        // 60 FPS = one update every 17 (16.667) ms. 30 FPS = one update every 34 (33.333) ms
    }

    /*----------------------------------------- WorldUpdate Methods -----------------------------------------*/

    void nextRound() {
        round++;
        spawnEnemies(round);
    }

    private void spawnEnemies(int round) {
        // TEMP TEST
        // TODO finish
        monsters.add(entityCreator.createMonster(50, 50, 10, 10, 5));
    }
}