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

// The "main" class for Model.
// Follows the facade pattern, this should be the only class in Model to communicate with controller and view.
public class Game {

    private final int worldMapRadius;
    private final ArrayList<Integer> playerInputArrayList;
    private final ArrayList<ViewObserver> viewObservers;
    private final EntityCreator entityCreator;
    private boolean enemiesSpawning;
    private int round;

    /*------------------------------------------------- Constructor -------------------------------------------------*/

    public Game(int worldMapRadius) {
        this.worldMapRadius = worldMapRadius;
        this.playerInputArrayList = new ArrayList<>();
        this.viewObservers = new ArrayList<>();
        this.entityCreator = new EntityCreator();
        this.enemiesSpawning = false;
        this.round = 0;
    }

    /*--------------------------------------------------- Getters ---------------------------------------------------*/

    public int getWorldMapRadius() {
        return worldMapRadius;
    }

    public ArrayList<Integer> getPlayerInputArrayList() {
        return playerInputArrayList;
    }

    public ArrayList<ViewObserver> getViewObservers() {
        return viewObservers;
    }

    public AddProjectile getProjectileCreator(){ //for player when creating weapon
        return entityCreator;
    }

    //public Player getPlayer() {return player;}

    public Position getPlayerPosition() {
        Position p = null;
        for (Entity e:entityCreator.getFriendlies()) {
            if (e.getEntityType() == EntityType.player ){
                p = e.getPosition();
            }
        }
        return p;

        //return player.getPosition();
    }

    public ArrayList<OnTick> getTickObservers(){
        return (ArrayList<OnTick>) entityCreator.getTickObservers();
    }

    public ArrayList<Entity> getEnemies() {
        return (ArrayList<Entity>) entityCreator.getEnemies();
    }

    public boolean isAnyEnemiesAlive() {
        return entityCreator.isAnyEnemiesAlive();
    }

    public ArrayList<Entity> getFriendlies() {
        return (ArrayList<Entity>) entityCreator.getFriendlies();
    }

    public ArrayList<Entity> getProjectiles() {
        return (ArrayList<Entity>) entityCreator.getProjectiles();
    }

    /*---------------------------------------- Public ViewObservers Methods ----------------------------------------*/

    public void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
    }

    /*--------------------------------------------- WorldUpdate Methods ---------------------------------------------*/

    public void startGame() {

        this.entityCreator.createPlayer(
                0,0, playerInputArrayList, WeaponFactory.getGun(getProjectileCreator()));

        Timer timer = new Timer();
        int period = 17;
        timer.scheduleAtFixedRate(new WorldUpdate(this, period), 0, period);
        // WorldUpdate runs as a thread, inputs are running parallel
        // 1. task 2. delay 3. period
        // 165 FPS = one update every 7  (6.0606) ms.
        // 144 FPS = one update every 7  (6.944) ms.
        // 60 FPS  = one update every 17 (16.667) ms.
        // 30 FPS  = one update every 34 (33.333) ms.
    }

    // Called when all enemies are dead
    void nextRound() {
        enemiesSpawning = true;
        round++;
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        int delay = 5;
        scheduler.schedule(new SpawnEnemies(this, entityCreator, round), delay, TimeUnit.SECONDS);
        System.out.println("ROUND: " + round);
        // 1. task 2. delay 3. time unit
    }

    void enemiesHaveSpawned() {
        enemiesSpawning = false;
    }

    boolean isEnemiesSpawning() {
        return enemiesSpawning;
    }
}