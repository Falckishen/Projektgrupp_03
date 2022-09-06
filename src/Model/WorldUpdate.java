package Model;

import java.util.TimerTask;

// Denna klass används som en TimerTask för att kunna uppdatera världen ett antal gånger per sekund (via metoden run)
public class WorldUpdate extends TimerTask {

    @Override
    public void run() {
        System.out.println("World updated!");

        /* KOD SOM KOMMER ANVÄNDAS

        if (System.currentTimeMillis() - scheduledExecutionTime() >= 1000) {
            System.out.println("Task");
            System.out.println(scheduledExecutionTime());
        }
        */
    }
}
