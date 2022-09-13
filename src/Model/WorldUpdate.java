package Model;

import java.util.TimerTask;
import java.util.ArrayList;
import Utilities.ViewObserver;

// This class is used as a TimerTask to update the world a amount of time every second
class WorldUpdate extends TimerTask {

    private final Game game;
    private final ArrayList<ViewObserver> viewObservers;
    private Player player;
    private boolean firstTick = true;

    public WorldUpdate(Game game) {
        this.game = game;
        this.viewObservers = new ArrayList<ViewObserver>();
    }

    @Override
    public void run() {
        while(firstTick) {
            // Create player
            player = new Player(0, 0, 25, 25, game.getCurrentPlayerDirections());
            game.setPlayer(player);
            firstTick = false;
        }

        player.doOnTick();

        /* THIS CODE WILL BE USED LATER

        if (System.currentTimeMillis() - scheduledExecutionTime() >= 1000) {
            System.out.println("Task");
            System.out.println(scheduledExecutionTime());
        }
        */

        // DRAWS WORLD
        for (ViewObserver viewObserver : viewObservers) {
            viewObserver.drawWorld();
        }
    }

    public void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
    }
}
