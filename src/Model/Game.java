package Model;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

// The "main" class for Model.
// Follows the facade pattern, this should be the only class in Model to communicate with controller and view
public class Game extends Thread {

    private Entity[] Entities;
    private List<Direction> listOfCurrentPlayerDirection;

    public Game() {

    }

    // This method runs as a thread, inputs are running parallel
    public void run() {

        TimerTask worldUpdate = new WorldUpdate();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(worldUpdate, 5000, 1000); // 1.task 2.delay 3.period
    }

    public void setListOfCurrentPlayerDirection(List<Direction> listOfCurrentPlayerDirection) {
        this.listOfCurrentPlayerDirection = listOfCurrentPlayerDirection;
    }

    public boolean isPositionOccupied(Position position) {
        for(Entity entity : Entities) {
            if (entity.getCoordX() == position.getX() && entity.getCoordY() == position.getY()) {
                return true;
            }
        }
        return false;
    }
}