package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TimerTask;

/**
 * The class responsible for updating the world and calling on the view to draw a new frame every 17 ms. Used as a
 * TimerTask for Timer.scheduleAtFixedRate().
 *
 * @author Samuel Falck
 */
class WorldUpdate extends TimerTask {

    private final Game game;
    private final OutputHandler outputHandler;
    private final int maxAllowedDelay;
    private final Iterable<TickObserver> tickObservers;

    /**
     * Creates an instance of WorldUpdate.
     *
     * @param game              a reference to the instance of Game.
     * @param maxAllowedDelay   the longest execution time (in ms) allowed for an update so as not to delay the next
     *                          update. Same as the time between executions of run(), 17 ms.
     */
    WorldUpdate(Game game, OutputHandler outputHandler, int maxAllowedDelay, Iterable<TickObserver> tickObservers) {
        this.game = game;
        this.outputHandler = outputHandler;
        this.maxAllowedDelay = maxAllowedDelay;
        this.tickObservers = tickObservers;
    }

    /**
     * Method executed every 17 ms by Timer.scheduleAtFixedRate(). If the last execution took longer than
     * maxAllowedDelay, it skips one update of the world and frame. If the player is dead, calls endGame() in Game.
     * Represents one tick.
     */
    @Override
    public void run() {
        int delay = (int) (System.currentTimeMillis() - scheduledExecutionTime());
        if(game.isPlayerDead()) {
            game.stopGame();
        }
        else if (delay <= maxAllowedDelay && !game.isGamePaused()) {
            updateWorld();
            outputHandler.updateGameFrame();
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
    }

    /**
     * Updates all objects in the model that needs to be updated every update (tickObservers).
     */
    private void updateTickObservers() {
        Iterable<TickObserver> observers = new ArrayList<>((Collection<? extends TickObserver>) tickObservers);
        observers.forEach(TickObserver::doOnTick);
    }
}