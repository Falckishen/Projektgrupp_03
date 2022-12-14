package Model;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import Model.Entities.Entity;
import Model.Entities.EntityCreator;

/**
 * Represents a game. Is the central class in the Model that binds the other classes. Acts as a facade, the view only
 * interacts with Game when it retrieves the required data to draw frames.
 *
 * @author Samuel Falck
 */
public class Game {

    private final MainMenu mainMenu;
    private final int difficulty;
    private final OutputHandler outputHandler;
    private final EntityCreator entityCreator;
    private final Timer timer;
    private int round;
    private boolean enemiesSpawning;
    private boolean gamePaused;

    /*------------------------------------------------- Constructor -------------------------------------------------*/

    /**
     * Creates an instance of Game and starts the game.
     *
     * @param mainMenu          reference to the MainMenu.
     * @param worldMapRadius    radius of the world map.
     * @param difficulty        difficulty.
     * @param playerInputList   reference to the list of current user keyboard input.
     * @param outputHandler     reference to the outputHandler.
     */

    public Game(MainMenu mainMenu, int worldMapRadius, int difficulty, List<Integer> playerInputList, List<Integer> weaponInputList, OutputHandler outputHandler) {

        this.mainMenu = mainMenu;
        this.difficulty = difficulty;
        this.outputHandler = outputHandler;
        this.entityCreator = new EntityCreator(worldMapRadius, difficulty);
        this.entityCreator.createWorldBorderWalls();
        this.timer = new Timer();
        this.round = 0;
        this.enemiesSpawning = false;
        this.gamePaused = false;

        startGame(playerInputList, weaponInputList);
    }

    /*------------------------------------------------ Public Getters ------------------------------------------------*/

    /**
     * Returns the position of player.
     *
     * @return position of player.
     */
    public Position getPlayerPosition() {
        return entityCreator.getPlayerPosition();
    }

    /**
     * Returns the current health of the player.
     *
     * @return current health of the player.
     */
    public int getPlayerHealth() {
        return entityCreator.getPlayerHealth();
    }

    /**
     * Return the Iterable of all current entities in the game.
     *
     * @return Iterable of entities.
     */
    public Iterable<Entity> getAllEntities(){
        return entityCreator.getAllEntities();
    }

    /**
     * Returns the current round.
     *
     * @return the current round.
     */
    public int getRound() {
        return round;
    }

    /**
     * Returns true if game is paused, false if game is not paused.
     *
     * @return true if game is paused, false if game is not paused.
     */
    public boolean isGamePaused() {
        return gamePaused;
    }

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
     * Returns true if player is dead, false if player is alive.
     *
     * @return true if player is dead, false if player is alive.
     */
    boolean isPlayerDead() {
        return !entityCreator.isPlayerAlive();
    }

    /*------------------------------------------------ Public Setters ------------------------------------------------*/

    /**
     * Pauses the game, sets gamePaused to true. updateWorld() in WorldUpdate stops running.
     */
    public void pauseGame() {
        gamePaused = true;
        outputHandler.showPauseMenu();
    }

    /**
     * Unpauses the game, sets gamePaused to false. updateWorld() in WorldUpdate starts running.
     */
    public void unPauseGame() {
        outputHandler.showGameScreen();
        gamePaused = false;
    }

    /*---------------------------------------------- New Round methods ----------------------------------------------*/

    /**
     * Starts the next round and spawn the enemies of that round after a 3 s delay. Creates an instance of SpawnEnemies
     * and its method run() is executed after a 3 s delay.
     */
    void nextRound() {
        enemiesSpawning = true;
        round++;
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(new SpawnEnemies(this, entityCreator, round, difficulty), 3, TimeUnit.SECONDS);
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
    private void startGame(List<Integer> playerInputList, List<Integer> weaponInputList) {
        entityCreator.createPlayer(0,0, playerInputList, weaponInputList);
        int period = 17;
        timer.scheduleAtFixedRate(new WorldUpdate(this, outputHandler, period, entityCreator.getTickObservers()), 0, period);
        /*
        Inputs are running parallel to WorldUpdate.
        1. task 2. delay 3. period
        165 FPS = one update every 7  (6.0606) ms.
        144 FPS = one update every 7  (6.944) ms.
        60 FPS  = one update every 17 (16.667) ms.
        30 FPS  = one update every 34 (33.333) ms.
        */
    }

    /**
     * Stops the game. The game ends, run() in WorldUpdate stops being executed and mainMenu.gameEnded() is called.
     */
    public void stopGame() {
        timer.cancel();
        timer.purge();

        System.out.println("Game over");

        mainMenu.gameEnded(round);
    }
}