package Model;

import Utilities.ViewObserver;
import java.util.ArrayList;
import java.util.TimerTask;

// This class is used as a TimerTask to update the world a amount of time every second
class WorldUpdate extends TimerTask {

    private Game game;
    private ArrayList<ViewObserver> viewObservers = new ArrayList<ViewObserver>();
    private Player player;
    private boolean firstTick = true;

    public WorldUpdate(Game game) {
        this.game = game;
    }

    public void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
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
}
