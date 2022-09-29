package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import Utilities.ViewObserver;

// This class is used as a TimerTask to update the world an amount of time every second
class WorldUpdate extends TimerTask {

    private final Game game;
    private final int maxAllowedDelay;
    private final List<ViewObserver> viewObservers;
    private final List<OnTick> tickObservers;

    WorldUpdate(Game game, int maxAllowedDelay) {
        this.game = game;
        this.maxAllowedDelay = maxAllowedDelay;
        this.viewObservers = game.getViewObservers();
        this.tickObservers = game.getTickObservers();
    }

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

    private void updateWorld() {
        updateTickObservers();

        if (!game.isEnemiesSpawning() && !game.isAnyEnemiesAlive()) {
            game.nextRound();
        }

        for (ViewObserver viewObserver : viewObservers) {
            viewObserver.drawFrame();
        }
    }

    private void updateTickObservers() {
        List<OnTick> ticks = new ArrayList<>(tickObservers);
        ticks.forEach(OnTick::doOnTick);
    }
}