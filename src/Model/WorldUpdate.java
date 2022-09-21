package Model;

import java.util.TimerTask;
import java.util.ArrayList;

import Utilities.ViewObserver;

// This class is used as a TimerTask to update the world an amount of time every second
class WorldUpdate extends TimerTask {

    private final Game game;
    private final ArrayList<ViewObserver> viewObservers;
    private final ArrayList<OnTick> tickObservers;
 //   private final OnTick player;
 //   private final ArrayList<Monster> monstersAlive;
    private final int maxAllowedDelay;

    public WorldUpdate(Game game, int maxAllowedDelay) {
        this.game = game;
        this.maxAllowedDelay = maxAllowedDelay;
        this.viewObservers = game.getViewObservers();
        this.tickObservers = game.getTickObservers();
    }

    @Override
    public void run() {
        int delay = (int) (System.currentTimeMillis() - scheduledExecutionTime());
        if (delay <= maxAllowedDelay) {
            updateWorld();
        }
        else {
            System.out.println("FRAME SKIPPED! Delay: " + delay + " ms");
        }
    }

    // New frame
    private void updateWorld() {
        if(!tickObservers.isEmpty()){
            for (OnTick observer: tickObservers) {
                observer.doOnTick();
            }
        }

        if (!game.isEnemiesSpawning() && !game.isAnyEnemiesAlive()) {
            game.nextRound();
        }

        // TODO collision

        // DRAWS WORLD
        for (ViewObserver viewObserver : viewObservers) {
            viewObserver.drawWorld();
        }
    }
}