package Model;

import java.util.List;
import java.util.TimerTask;
import java.util.ArrayList;
import Utilities.ViewObserver;

// This class is used as a TimerTask to update the world an amount of time every second
class WorldUpdate extends TimerTask {

    private final Game game;
    private final int maxAllowedDelay;
    private final ArrayList<ViewObserver> viewObservers;
    private final ArrayList<OnTick> tickObservers;

    WorldUpdate(Game game, int maxAllowedDelay) {
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

    private void updateWorld() {
        List<OnTick> ticks = new ArrayList<>();
        ticks.addAll(tickObservers);
//        for (OnTick tickObserver : tickObservers) {
//            ticks.add(tickObserver);
//        }

        ticks.forEach(onTick -> onTick.doOnTick());

        if (!game.isEnemiesSpawning() && !game.isAnyEnemiesAlive()) {
            game.nextRound();
        }

        for (ViewObserver viewObserver : viewObservers) {
            viewObserver.drawFrame();
        }
    }
}