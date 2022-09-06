package Model;

import java.util.Timer;
import java.util.TimerTask;

// Game engine, main class for Model.
// Follows the facade pattern, this is the only public class in Model
public class Game extends Thread {

    private Entity[] Entities;

    public Game() {

    }

    // Denna metod körs som en thread, så eventuella inputs körs parallelt med denna
    public void run() {

        TimerTask worldUpdate = new WorldUpdate();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(worldUpdate, 5000, 1000); // 1.task 2.delay 3.period
    }

    // Controller calls this method to move the player
    public void movePlayer() {

    }

    public Entity getEntityOnPosition(Position position) {
        for(Entity entity : Entities) {
            if (entity.getCoordX() == position.getX() && entity.getCoordY() == position.getY()) {
                return entity;
            }
        }
        return null;
    }

    public boolean isPositionOccupied(Position position) {
        return getEntityOnPosition(position) != null;
    }
}