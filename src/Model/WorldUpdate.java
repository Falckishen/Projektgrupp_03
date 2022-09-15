package Model;

import java.util.TimerTask;
import java.util.ArrayList;
import Utilities.ViewObserver;

// This class is used as a TimerTask to update the world an amount of time every second
class WorldUpdate extends TimerTask {

    private final ArrayList<ViewObserver> viewObservers;
    private final Player player;
    private final ArrayList<Monster> monsters;

    // TODO add a parameter "walls".
    public WorldUpdate(ArrayList<ViewObserver> viewObservers, Player player, ArrayList<Monster> monsters) {
        this.viewObservers = viewObservers;
        this.player = player;
        this.monsters = monsters;
    }

    // WorldUpdate
    @Override
    public void run() {

        /*
        THIS CODE WILL BE USED LATER

        if (System.currentTimeMillis() - scheduledExecutionTime() >= 1000) {
            System.out.println("Task");
            System.out.println(scheduledExecutionTime());
        }
        */

        player.doOnTick();

        if (!monsters.isEmpty()) {
            for(Monster monster : monsters) {
                monster.doOnTick();
            }
        }

        // TODO collision

        // DRAWS WORLD
        for (ViewObserver viewObserver : viewObservers) {
            viewObserver.drawWorld();
        }
    }

    public void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
    }
}