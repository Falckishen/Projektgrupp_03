package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import Model.Entities.*;
import Model.Weapons.WeaponFactory;
import Utilities.EntityType;
import Utilities.Position;
import Utilities.ViewObserver;

// The "main" class for Model
// Follows the facade pattern, this should be the only class in Model to communicate with controller and view.
public class Game {

    private final int worldMapRadius;
    private final int difficulty;
    private int round;
    private boolean enemiesSpawning;
    private final ArrayList<Integer> playerInputArrayList;
    private final ArrayList<ViewObserver> viewObservers;
    private final EntityCreator entityCreator;

    /*------------------------------------------------- Constructor -------------------------------------------------*/

    public Game(int worldMapRadius, int difficulty) {
        this.worldMapRadius = worldMapRadius;
        this.difficulty = difficulty;
        this.round = 0;
        this.enemiesSpawning = false;
        this.playerInputArrayList = new ArrayList<>();
        this.viewObservers = new ArrayList<>();
        this.entityCreator = new EntityCreator();
    }

    /*--------------------------------------------------- Getters ---------------------------------------------------*/

    public int getWorldMapRadius() {
        return worldMapRadius;
    }

    public Position getPlayerPosition() {
        Position p = null;
        for (MovableEntity e:entityCreator.getFriendlies()) {
            if (e.getEntityType() == EntityType.player ){
                p = e.getPosition();
            }
        }
        return p;

        //return player.getPosition();
    }

    public boolean isAnyEnemiesAlive() {
        return entityCreator.isAnyEnemiesAlive();
    }

    public ArrayList<Integer> getPlayerInputArrayList() {
        return playerInputArrayList;
    }

    public ArrayList<ViewObserver> getViewObservers() {
        return viewObservers;
    }

    public ArrayList<OnTick> getTickObservers(){
        return (ArrayList<OnTick>) entityCreator.getTickObservers();
    }

    public ArrayList<MovableEntity> getEnemies() {
        return (ArrayList<MovableEntity>) entityCreator.getEnemies();
    }

    public ArrayList<MovableEntity> getFriendlies() {
        return (ArrayList<MovableEntity>) entityCreator.getFriendlies();
    }

    /*---------------------------------------- Public ViewObservers Methods ----------------------------------------*/

    public void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
    }

    /*--------------------------------------------- WorldUpdate Methods ---------------------------------------------*/

    public void startGame() {
        entityCreator.createPlayer(0,0, playerInputArrayList, WeaponFactory.getGun(entityCreator));
        Timer timer = new Timer();
        int period = 17;
        timer.scheduleAtFixedRate(new WorldUpdate(this, period), 0, period);
        /*
        WorldUpdate runs as a thread, inputs are running parallel
        1. task 2. delay 3. period
        165 FPS = one update every 7  (6.0606) ms.
        144 FPS = one update every 7  (6.944) ms.
        60 FPS  = one update every 17 (16.667) ms.
        30 FPS  = one update every 34 (33.333) ms.
        */
    }

    /*---------------------------------------------- New Round Methods ----------------------------------------------*/

    void nextRound() {
        enemiesSpawning = true;
        round++;
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        int delay = 5;
        scheduler.schedule(new SpawnEnemies(this, entityCreator, round, difficulty), delay, TimeUnit.SECONDS);
        System.out.println("ROUND: " + round);
    }

    void enemiesHaveSpawned() {
        enemiesSpawning = false;
    }

    boolean isEnemiesSpawning() {
        return enemiesSpawning;
    }
}