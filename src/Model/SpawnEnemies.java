package Model;

import Model.Entities.AddEnemy;

/**
 * The class responsible for spawning enemies every new round. Used as a Runnable for
 * ScheduledExecutorService.schedule().
 *
 * @author Samuel Falck
 */
class SpawnEnemies implements Runnable {

    private final Game game;
    private final AddEnemy enemyEntityCreator;
    private final int round;
    private final int difficulty;

    /**
     * Creates an instance of SpawnEnemies.
     *
     * @param game                  a reference to the instance of Game.
     * @param enemyEntityCreator    a reference to the instance of EntityCreator.
     * @param round                 the current round.
     * @param difficulty            the difficulty.
     */
    SpawnEnemies(Game game, AddEnemy enemyEntityCreator, int round, int difficulty) {
        this.game = game;
        this.enemyEntityCreator = enemyEntityCreator;
        this.round = round;
        this.difficulty = difficulty;
    }

    /**
     * Spawns the enemies of the next round. Executed by ScheduledExecutorService.schedule() 5 s after the previous
     * round ended.
     */
    @Override
    public void run() {
        int numberOfNewEnemies = numberOfNewEnemies();
        for (int i = 0; i < numberOfNewEnemies; i++) {
            enemyEntityCreator.createMonster();
        }
        game.enemiesHaveSpawned();
        System.out.println("ENEMIES SPAWNED: " + numberOfNewEnemies); // Debug
    }

    /**
     * Calculate and return the number of enemies to be spawned next round.
     *
     * @return 1 if it is the first round. Otherwise, (round^2)*difficulty/2 rounded down to the nearest integer.
     */
    private int numberOfNewEnemies() {
        if (round == 1) {
            return 1;
        }
        return (int) (Math.pow(round, 2)*difficulty/2);
    }
}