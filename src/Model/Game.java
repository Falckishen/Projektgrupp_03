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
 * The "main" class for Model that connects the other classes, responsible for starting the game. The only public
 * class in Model, functions as a facade of Model for the views. One instance represents one game.
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
     * Creates an instance of Game and starts a game.
     *
     * @param worldMapRadius    radius of the world map.
     * @param difficulty        difficulty of the game.
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

        // TODO startGame()
    }

    /*--------------------------------------------------- Getters ---------------------------------------------------*/

    /**
     * @return position of the player.
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
     * @return true if any enemy is alive, false if all enemies is dead.
     */
    public boolean isAnyEnemiesAlive() {
        return entityCreator.isAnyEnemiesAlive();
    }

    /**
     * @return list of the current player input.
     */
    public ArrayList<Integer> getPlayerInputList() {
        return playerInputList;
    }

    public List<ViewObserver> getViewObservers() {
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

    public ArrayList<MovableEntity> getProjectiles() {
        return (ArrayList<MovableEntity>) entityCreator.getProjectiles();
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public boolean isPlayerDead() {
        return !entityCreator.isPlayerAlive();
    }

    /*--------------------------------------------------- Setters ---------------------------------------------------*/

    public void pauseGame() {
        this.gamePaused = true;
    }

    public void unPauseGame() {
        this.gamePaused = false;
    }

    /*---------------------------------------- Public ViewObservers Methods ----------------------------------------*/

    public void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
    }

    /*--------------------------------------------- WorldUpdate Methods ---------------------------------------------*/

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

    public void endGame() {
        timer.cancel();
        timer.purge();

        // TODO kolla om round är high score

        // TODO visa game-over skärm
    }

    /*---------------------------------------------- New Round Methods ----------------------------------------------*/

    void nextRound() {
        enemiesSpawning = true;
        round++;

        int delay = 5;
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
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