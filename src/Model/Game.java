package Model;
import Utilities.Direction;

import Utilities.ViewObserver;
import java.util.ArrayList;
import java.util.Timer;

// The "main" class for Model.
// Follows the facade pattern, this should be the only class in Model to communicate with controller and view
public class Game extends Thread {

    private WorldUpdate worldUpdate;
    private Entity[] Entities;
    private ArrayList<Direction> currentPlayerDirections;
    private Player player;

    public Game() {
        worldUpdate = new WorldUpdate(this);
    }

    public void addViewObserver(ViewObserver viewObserver) {
        worldUpdate.addViewObserver(viewObserver);
    }

    // This method runs as a thread, inputs are running parallel
    public void run() {
        //TimerTask worldUpdateTimerTask = worldUpdate;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(worldUpdate, 0, 17); // 1.task 2. delay 3. period
        // 60 FPS = one update every 17 (16.667) ms. 30 FPS = one update every 34 (33.333) ms
    }

    public ArrayList<Direction> getCurrentPlayerDirections() {
        return currentPlayerDirections;
    }

    public void setCurrentPlayerDirections(ArrayList<Direction> currentPlayerDirections) {
        this.currentPlayerDirections = currentPlayerDirections;
    }

    public Position getPlayerPosition() {
        return player.getCurrentPosition();
    }

    void setPlayer(Player player) {
        this.player = player;
    }
}