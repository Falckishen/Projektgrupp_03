package Model;

import Utilities.ViewObserver;

import java.util.ArrayList;
import java.util.TimerTask;

// This class is used as a TimerTask to update the world a amount of time every second
class WorldUpdate extends TimerTask {

    private ArrayList<ViewObserver> viewObservers;

    public WorldUpdate() {

    }

    public void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
    }

    @Override
    public void run() {
        //Test
        System.out.println("World updated!");


        /* THIS CODE WILL BE USED LATER

        if (System.currentTimeMillis() - scheduledExecutionTime() >= 1000) {
            System.out.println("Task");
            System.out.println(scheduledExecutionTime());
        }
        */

        for (ViewObserver viewObserver : viewObservers) {
            viewObserver.drawWorld();
        }
    }
}
