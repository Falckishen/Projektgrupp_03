package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import Model.Entities.EntityCreator;
import Model.Entities.MovableEntity;
import Utilities.EntityType;
import Utilities.Position;
import Utilities.ViewObserver;

/**
 * The "main" class for the model that connects the other classes, responsible for starting the game. The only public
 * class in the model, functions as a facade of the model for the views. One instance represents one game.
 */
public class Game {

    private final int difficulty;
    private final ArrayList<Integer> playerInputList;
    private final List<ViewObserver> viewObservers;
    private final EntityCreator entityCreator;
    private final Timer timer;
    private int round;
    private boolean enemiesSpawning;
    private boolean gamePaused;

    /*------------------------------------------------- Constructor -------------------------------------------------*/

    /**
     * Creates an instance of Game.
     *
     * @param worldMapRadius    the radius of the world map.
     * @param difficulty        the difficulty of the game.
     */
    public Game(int worldMapRadius, int difficulty) {
        this.difficulty = difficulty;
        this.playerInputList = new ArrayList<>();
        this.viewObservers = new ArrayList<>();
        this.entityCreator = new EntityCreator(worldMapRadius);
        this.timer = new Timer();
        this.round = 0;
        this.enemiesSpawning = false;
        this.gamePaused = false;
    }

    /*--------------------------------------------------- Getters ---------------------------------------------------*/

    /**
     * @return position of player.
     */
    public Position getPlayerPosition() {
        Position p = null;
        for (MovableEntity e:entityCreator.getFriendlies()) {
            if (e.getEntityType() == EntityType.player ){
                p = e.getPosition();
            }
        }
        return p;
    }

    /**
     * @return true if any enemy is alive, false if all enemies are dead.
     */
    public boolean isAnyEnemiesAlive() {
        return entityCreator.isAnyEnemiesAlive();
    }

    /**
     * @return list of current user keyboard input.
     */
    public ArrayList<Integer> getPlayerInputList() {
        return playerInputList;
    }

    /**
     * @return list of the views.
     */
    public List<ViewObserver> getViewObservers() {
        return viewObservers;
    }

    /**
     * @return list of the tick observers.
     */
    public ArrayList<OnTick> getTickObservers(){
        return (ArrayList<OnTick>) entityCreator.getTickObservers();
    }

    /**
     * @return list of enemies alive.
     */
    public ArrayList<MovableEntity> getEnemies() {
        return (ArrayList<MovableEntity>) entityCreator.getEnemies();
    }

    /**
     * @return list of friendlies alive.
     */
    public ArrayList<MovableEntity> getFriendlies() {
        return (ArrayList<MovableEntity>) entityCreator.getFriendlies();
    }

    /**
     * @return list of projectiles.
     */
    public ArrayList<MovableEntity> getProjectiles() {
        return (ArrayList<MovableEntity>) entityCreator.getProjectiles();
    }

    /**
     * @return true if game is paused, false if game is not paused.
     */
    public boolean isGamePaused() {
        return gamePaused;
    }

    /**
     * @return true if player is dead, false if player is alive.
     */
    public boolean isPlayerDead() {
        return !entityCreator.isPlayerAlive();
    }

    /*--------------------------------------------------- Setters ---------------------------------------------------*/

    /**
     * Pauses the game, sets gamePaused to true. updateWorld() in WorldUpdate stops running.
     */
    public void pauseGame() {
        this.gamePaused = true;
    }

    /**
     * Unpauses the game, sets gamePaused to false. updateWorld() in WorldUpdate starts running.
     */
    public void unPauseGame() {
        this.gamePaused = false;
    }

    /*---------------------------------------- Public ViewObservers Methods ----------------------------------------*/

    /**
     * Adds a view to become an observer of the model.
     *
     * @param viewObserver the view to be added as an observer of the model.
     */
    public void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
    }

    /*--------------------------------------------- WorldUpdate Methods ---------------------------------------------*/

    /**
     * Start the game, the world starts updating and round one start. Creates an instance of WorldUpdate and its method
     * run() is executed every 17 ms. An instance of Player is created.
     */
    public void startGame() {
        entityCreator.createPlayer(0,0, playerInputList);
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

    /**
     * Stops the game, the world and the frame stops updating. run() in WorldUpdate stops being executed.
     */
    public void endGame() {
        timer.cancel();
        timer.purge();

        // TODO kolla om round är high score
        // TODO visa game-over skärm
    }

    /*---------------------------------------------- New Round Methods ----------------------------------------------*/

    /**
     * Starts the next round and spawn the enemies of that round after a 5 s delay. Creates an instance of SpawnEnemies
     * and its method run() is executed after a 5 s delay.
     */
    void nextRound() {
        enemiesSpawning = true;
        round++;
        int delay = 5;
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(new SpawnEnemies(this, entityCreator, round, difficulty), delay, TimeUnit.SECONDS);
        System.out.println("ROUND: " + round);
    }

    /**
     * Set enemiesSpawning to false. Called when SpawnEnemies has spawned enemies.
     */
    void enemiesHaveSpawned() {
        enemiesSpawning = false;
    }

    /**
     * @return true if SpawnEnemies has spawned enemies, false if SpawnEnemies has not yet spawned enemies.
     */
    boolean isEnemiesSpawning() {
        return enemiesSpawning;
    }
}