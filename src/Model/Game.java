package Model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import Model.Entities.Entity;
import Model.Entities.EntityCreator;
import Model.Entities.MovableEntity;
import Utilities.EntityType;
import Utilities.Position;
import Utilities.ViewObserver;

/**
 * The "main" class for the model that connects the other classes, responsible for starting the game. The only public
 * class in the model, functions as a facade of the model for the views. One instance represents one game.
 *
 * @author Samuel Falck
 */
public class Game {

    private final String gameName;
    private final int difficulty;
    private final List<Integer> playerInputList;
    private final Iterable<ViewObserver> viewObservers;
    private final EntityCreator entityCreator;
    private final Timer timer;
    private int round;
    private boolean enemiesSpawning;
    private boolean gamePaused;

    /*------------------------------------------------- Constructor -------------------------------------------------*/

    /**
     * Creates an instance of Game.
     *
     * @param gameName          the name of the game.
     * @param worldMapRadius    the radius of the world map.
     * @param difficulty        the difficulty of the game.
     */
    public Game(String gameName, int worldMapRadius, int difficulty, List<Integer> playerInputList, Iterable<ViewObserver> viewObservers) {
        this.gameName = gameName;
        this.difficulty = difficulty;
        this.playerInputList = playerInputList;
        this.viewObservers = viewObservers;
        this.entityCreator = new EntityCreator(worldMapRadius);
        this.entityCreator.createWorldBorderWalls();
        this.timer = new Timer();
        this.round = 0;
        this.enemiesSpawning = false;
        this.gamePaused = false;

        startGame();
    }

    /*------------------------------------------------ Public Getters ------------------------------------------------*/

    /**
     * Returns the position of player.
     *
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
     * Returns the list of enemies alive.
     *
     * @return list of enemies alive.
     */
    public Iterable<MovableEntity> getEnemies() {
        return (Iterable<MovableEntity>) entityCreator.getEnemies();
    }

    /**
     * Returns the list of friendlies alive.
     *
     * @return list of friendlies alive.
     */
    public Iterable<MovableEntity> getFriendlies() {
        return (Iterable<MovableEntity>) entityCreator.getFriendlies();
    }

    /**
     * Returns the list of projectiles.
     *
     * @return list of projectiles.
     */
    public Iterable<MovableEntity> getProjectiles() {
        return (Iterable<MovableEntity>) entityCreator.getProjectiles();
    }

    /**
     * Return the list of non-living objects.
     *
     * @return List of non-living objects.
     */
    public Iterable<Entity> getNonLivingObjects() {
        return (Iterable<Entity>) entityCreator.getNonLivingObjects();
    }

    /*
    public int getPlayerHealth() {
    //TODO fixa
    }
    */

    /*------------------------------------------- Package-private Getters -------------------------------------------*/

    /**
     * Returns true if any enemy is alive, false if all enemies are dead.
     *
     * @return true if any enemy is alive, false if all enemies are dead.
     */
    boolean isAnyEnemiesAlive() {
        return entityCreator.isAnyEnemiesAlive();
    }

    /**
     * Returns the list of the views.
     *
     * @return list of the views.
     */
    Iterable<ViewObserver> getViewObservers() {
        return viewObservers;
    }

    /**
     * Returns the list of the tick observers.
     *
     * @return list of the tick observers.
     */
    Iterable<OnTick> getTickObservers(){
        return entityCreator.getTickObservers();
    }

    /**
     * Returns true if game is paused, false if game is not paused.
     *
     * @return true if game is paused, false if game is not paused.
     */
    boolean isGamePaused() {
        return gamePaused;
    }

    /**
     * Returns true if player is dead, false if player is alive.
     *
     * @return true if player is dead, false if player is alive.
     */
    boolean isPlayerDead() {
        return !entityCreator.isPlayerAlive();
    }

    int getRound() {
        return round;
    }

    /*------------------------------------------------ Public Setters ------------------------------------------------*/

    /**
     * Pauses the game, sets gamePaused to true. updateWorld() in WorldUpdate stops running.
     */
    public void pauseGame() {
        gamePaused = true;
    }

    /**
     * Unpauses the game, sets gamePaused to false. updateWorld() in WorldUpdate starts running.
     */
    public void unPauseGame() {
        gamePaused = false;
    }

    /*---------------------------------------------- New Round methods ----------------------------------------------*/

    /**
     * Starts the next round and spawn the enemies of that round after a 5 s delay. Creates an instance of SpawnEnemies
     * and its method run() is executed after a 5 s delay.
     */
    void nextRound() {
        enemiesSpawning = true;
        round++;
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(new SpawnEnemies(this, entityCreator, round, difficulty), 5, TimeUnit.SECONDS);
        System.out.println("ROUND: " + round);
    }

    /**
     * Set enemiesSpawning to false. Called when SpawnEnemies has spawned enemies.
     */
    void enemiesHaveSpawned() {
        enemiesSpawning = false;
    }

    /**
     * Returns true if SpawnEnemies has spawned enemies, false if SpawnEnemies has not yet spawned enemies.
     *
     * @return true if SpawnEnemies has spawned enemies, false if SpawnEnemies has not yet spawned enemies.
     */
    boolean isEnemiesSpawning() {
        return enemiesSpawning;
    }

    /*------------------------------------------- Start/Stop game methods -------------------------------------------*/

    /**
     * Start the game, the world starts updating and round one start. Creates an instance of WorldUpdate and its method
     * run() is executed every 17 ms. An instance of Player is created.
     */
    private void startGame() {
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
     * Stops the game, the game ends, a game over-screen appears, and if the old high score was beaten the score of this
     * game is saved as the new high score. run() in WorldUpdate stops being executed.
     */
    void endGame() {
        timer.cancel();
        timer.purge();

        System.out.println("Game ended");
    }
}