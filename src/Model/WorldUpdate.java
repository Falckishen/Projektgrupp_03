package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import Utilities.ViewObserver;

/**
 * The class responsible for updating the world and calling on the view to draw a new frame every 17 ms. Used as a
 * TimerTask for Timer.scheduleAtFixedRate().
 *
 * @author Samuel Falck
 */
class WorldUpdate extends TimerTask {

    private final Game game;
    private final int maxAllowedDelay;
    private final List<ViewObserver> viewObservers;
    private final List<OnTick> tickObservers;

    /**
     * Creates an instance of WorldUpdate.
     *
     * @param game              a reference to the instance of Game.
     * @param maxAllowedDelay   the longest execution time (in ms) allowed for an update so as not to delay the next
     *                          update. Same as the time between executions of run(), 17 ms.
     */
    WorldUpdate(Game game, int maxAllowedDelay) {
        this.game = game;
        this.maxAllowedDelay = maxAllowedDelay;
        this.viewObservers = game.getViewObservers();
        this.tickObservers = game.getTickObservers();
    }

    /**
     * The method executed every 17 ms by Timer.scheduleAtFixedRate(). If the last execution took longer than
     * maxAllowedDelay, it skips one update of the world and frame. If the player is dead, calls endGame() in Game.
     */
    @Override
    public void run() {
        if(game.isPlayerDead()) {
            game.endGame();
        }
        int delay = (int) (System.currentTimeMillis() - scheduledExecutionTime());
        if (delay <= maxAllowedDelay && !game.isGamePaused()) {
            updateWorld();
        }
        else if(delay > maxAllowedDelay) {
            System.out.println("FRAME SKIPPED! Lag: " + delay + " ms");
        }
    }

    /**
     * Updates the world and calls on the views to draw a new frame.
     */
    private void updateWorld() {
        updateTickObservers();

        if (!game.isEnemiesSpawning() && !game.isAnyEnemiesAlive()) {
            game.nextRound();
        }

        viewObservers.forEach(ViewObserver::drawFrame);
    }

    /**
     * Updates all objects in the model that needs to be updated every update (tickObservers).
     */
    private void updateTickObservers() {
        List<OnTick> ticks = new ArrayList<>(tickObservers);
        ticks.forEach(OnTick::doOnTick);
    }
}