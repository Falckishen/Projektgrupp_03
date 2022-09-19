package Model;

import java.util.TimerTask;
import java.util.ArrayList;

import Model.Entities.Monster;
import Model.Entities.Player;
import Utilities.ViewObserver;

// This class is used as a TimerTask to update the world an amount of time every second
class WorldUpdate extends TimerTask {

    private final Game game;
    private final ArrayList<ViewObserver> viewObservers;
    private final Player player;
    private final ArrayList<Monster> monstersAlive;
    private final int maxAllowedDelay;

    public WorldUpdate(Game game, int maxAllowedDelay) {
        this.game = game;
        this.maxAllowedDelay = maxAllowedDelay;
        this.viewObservers = game.getViewObservers();
        this.player = game.getPlayer();
        this.monstersAlive = game.getMonstersAlive();
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
        player.doOnTick();

        if (!monstersAlive.isEmpty()) {
            for(Monster monster : monstersAlive) {
                monster.doOnTick();
            }
        }
        else if (!game.isEnemiesSpawning()) {
            game.nextRound();
        }

        // TODO collision

        // DRAWS WORLD
        for (ViewObserver viewObserver : viewObservers) {
            viewObserver.drawWorld();
        }
    }
}