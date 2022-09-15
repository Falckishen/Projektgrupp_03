package Model;

import java.util.ArrayList;
import java.util.Timer;
import Utilities.Direction;
import Utilities.ViewObserver;

// The "main" class for Model.
// Follows the facade pattern, this should be the only class in Model to communicate with controller and view.
public class Game extends Thread {

    private final ArrayList<ViewObserver> viewObservers;
    private Player player;
    private ArrayList<Monster> monsters;
    private ArrayList<Direction> currentPlayerDirections;

    /*------------------------------------------------- Constructor -------------------------------------------------*/

    public Game() {
        this.viewObservers = new ArrayList<>();
    }

    /*--------------------------------------------------- Getters ---------------------------------------------------*/
    // For the View

    public Position getPlayerPosition() {
        return player.getCurrentPosition();
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    /*--------------------------------------------------- Setters ---------------------------------------------------*/

    public void setCurrentPlayerDirections(ArrayList<Direction> currentPlayerDirections) {
        this.currentPlayerDirections = currentPlayerDirections;
    }

    /*-------------------------------------------------- Threading --------------------------------------------------*/

    // This method runs as a thread, inputs are running parallel
    public void run() {
        this.player = new Player(0,0,25,25, currentPlayerDirections);
        this.monsters = new ArrayList<>();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new WorldUpdate(viewObservers, player, monsters), 0, 17);
        // 1.task 2. delay 3. period
        // 60 FPS = one update every 17 (16.667) ms. 30 FPS = one update every 34 (33.333) ms
    }

    /*---------------------------------------- Public ViewObservers Methods ----------------------------------------*/

    public void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
    }
}