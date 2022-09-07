package Model;

import java.util.TimerTask;

// This class is used as a TimerTask to update the world a amount of time every second
class WorldUpdate extends TimerTask {

    @Override
    public void run() {
        //Test
        System.out.println("World updated!");



        /* THIS CODE WILL BE USED LATER */

        if (System.currentTimeMillis() - scheduledExecutionTime() >= 1000) {
            System.out.println("Task");
            System.out.println(scheduledExecutionTime());
        }
    }
}
