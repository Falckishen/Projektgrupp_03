package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Model.Entities.*;
import Model.Weapons.WeaponFactory;
import Utilities.Direction;
import Utilities.ViewObserver;

// The "main" class for Model.
// Follows the facade pattern, this should be the only class in Model to communicate with controller and view.
public class Game {

    private ArrayList<ViewObserver> viewObservers;
    private ArrayList<OnTick> tickObservers;
    private Player player;
   // private ArrayList<Monster> monstersAlive;
    private ArrayList<Direction> currentPlayerDirections;
    private int round;
    private final EntityCreator entityCreator;
    private boolean enemiesSpawning;

    /*------------------------------------------------- Constructor -------------------------------------------------*/

    public Game() {
        this.viewObservers = new ArrayList<>();
        this.round = 0;
        this.entityCreator = new EntityCreator();
        this.enemiesSpawning = false;

        List<OnTick> viewObservers1;
        viewObservers1 = new ArrayList<>();
        viewObservers1 = entityCreator.getTickObservers();
        this.tickObservers = (ArrayList)viewObservers1;
    }

    /*--------------------------------------------------- Getters ---------------------------------------------------*/

    public ArrayList<ViewObserver> getViewObservers() {
        return viewObservers;
    }

    public AddProjectile getProjectileCreator(){ //for player when creating weapon
        return entityCreator;
    }

    public Player getPlayer() {
        return player;
    }

    public Position getPlayerPosition() {
        return player.getCurrentPosition();
    }
    /*
    public ArrayList<Monster> getMonstersAlive() {
        return monstersAlive;
    }*/

    public ArrayList<OnTick> getTickObservers(){
        return tickObservers;
    }

    /*--------------------------------------------------- Setters ---------------------------------------------------*/

    public void setCurrentPlayerDirections(ArrayList<Direction> currentPlayerDirections) {
        this.currentPlayerDirections = currentPlayerDirections;
    }

    /*---------------------------------------- Public ViewObservers Methods ----------------------------------------*/

    public void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
    }

    /*--------------------------------------------- WorldUpdate Methods ---------------------------------------------*/

    public void startGame() {
        this.player = this.entityCreator.createPlayer(0,0,currentPlayerDirections, WeaponFactory.getGun(getProjectileCreator()));

       // this.player = entityCreator.createPlayer(0,0, currentPlayerDirections);
      //  this.monstersAlive = new ArrayList<>();

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
        scheduler.schedule(new SpawnEnemies(this, entityCreator, round, entityCreator.getEnemiesExist()), delay, TimeUnit.SECONDS);
        // 1. task 2. delay 3. time unit
    }

    void enemiesHaveSpawned() {
        System.out.println("Round " + round + " enemies have spawned!");
        enemiesSpawning = false;
    }

    boolean isEnemiesSpawning() {
        return enemiesSpawning;
    }
}