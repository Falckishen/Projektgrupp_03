package Model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
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
    private final ArrayList<Integer> playerInputList;
    private final List<ViewObserver> viewObservers;
    private final EntityCreator entityCreator;
    private final Timer timer;
    private int round;
    private boolean enemiesSpawning;
    private boolean gamePaused;

    /*------------------------------------------------- Constructor -------------------------------------------------*/

    // TODO: Gör om alla listor som endast ska itereras till itreble

    /**
     * Creates an instance of Game.
     *
     * @param gameName          the name of the game.
     * @param worldMapRadius    the radius of the world map.
     * @param difficulty        the difficulty of the game.
     */
    public Game(String gameName, int worldMapRadius, int difficulty) {
        this.gameName = gameName;
        this.difficulty = difficulty;
        this.playerInputList = new ArrayList<>();
        this.viewObservers = new ArrayList<>();
        this.entityCreator = new EntityCreator(worldMapRadius);
        this.entityCreator.createWorldBorderWalls();
        this.timer = new Timer();
        this.round = 0;
        this.enemiesSpawning = false;
        this.gamePaused = false;
    }

    /*--------------------------------------------------- Getters ---------------------------------------------------*/

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
     * Returns true if any enemy is alive, false if all enemies are dead.
     *
     * @return true if any enemy is alive, false if all enemies are dead.
     */
    boolean isAnyEnemiesAlive() {
        return entityCreator.isAnyEnemiesAlive();
    }

    /**
     * Returns the list of current user keyboard input.
     *
     * @return list of current user keyboard input.
     */
    public ArrayList<Integer> getPlayerInputList() {
        return playerInputList;
    }

    /**
     * Returns the list of the views.
     *
     * @return list of the views.
     */
    List<ViewObserver> getViewObservers() {
        return viewObservers;
    }

    /**
     * Returns the list of the tick observers.
     *
     * @return list of the tick observers.
     */
    List<OnTick> getTickObservers(){
        return entityCreator.getTickObservers();
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

    public Iterable<Entity> getNonLivingObjects() {
        return (Iterable<Entity>) entityCreator.getNonLivingObjects();
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

    /**
     * Returns the old high score.
     *
     * @return the old high score.
     * @throws Exception if there is an error reading the old high score in
     * C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt\high score.txt.
     */
    public int getHighScore() throws Exception {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(getHighScoreFile()), StandardCharsets.UTF_8));
            int highScore = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
            return highScore;
        }
        catch (Exception exception) {
            throw new Exception("Error when reading old high score: " + exception.getMessage());
        }
    }

    /**
     * Returns true if the score of this round is higher than the high score, otherwise false.
     *
     * @return true if the score of this round is higher than the high score, otherwise false.
     */
    public boolean wasHighScoreBeaten() {
        try {
            return round > getHighScore();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return true;
        }
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

    /*-------------------------------------------- ViewObservers methods --------------------------------------------*/

    /**
     * Adds a view to become an observer of the model.
     *
     * @param viewObserver the view to be added as an observer of the model.
     */
    public void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
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
     * Stops the game, the game ends, a game over-screen appears, and if the old high score was beaten the score of this
     * game is saved as the new high score. run() in WorldUpdate stops being executed.
     */
    public void endGame() {
        timer.cancel();
        timer.purge();

        if (new File(getHighScoreFolderPath()).mkdir()) {
            System.out.println(getHighScoreFolderPath() + " was created");
            saveNewHighScore();
        }
        else if (wasHighScoreBeaten()) {
            saveNewHighScore();
        }

        // TODO visa game-over skärm
    }

    /*------------------------------------------- saveHighScore methods -------------------------------------------*/

    /**
     * The new HighScore is saved in C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt\high score.txt.
     */
    private void saveNewHighScore() {
        System.out.println("NEW HIGH SCORE! " + round + "!");
        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream(getHighScoreFile()), StandardCharsets.UTF_8);
            writer.write(String.valueOf(round));
            writer.close();
        }
        catch (Exception exception) {
            System.out.println("Error when saving high score: " + exception.getMessage());
        }
    }

    /**
     * Returns the File C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt\high score.txt.
     *
     * @return file C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt\high score.txt.
     */
    private File getHighScoreFile() {
        return new File(getHighScoreFolderPath() + "\\high score.txt");
    }

    /**
     * Returns "C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt"
     *
     * @return "C:\Users\%UserProfile%\Documents\Projektgrupp 3 projekt".
     */
    private String getHighScoreFolderPath() {
        return System.getProperty("user.home") + "\\Documents\\" + gameName;
    }
}