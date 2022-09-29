package Model;

import Model.Entities.AddEnemy;

/**
 * The class responsible for spawning enemies every new round.
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
     * @param game                  reference to the instance of Game.
     * @param enemyEntityCreator    reference to the instance of EntityCreator.
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
     * Spawns enemies. Should run a couple of seconds after the previous round ended.
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
     * Calculate and return the number if enemies to be spawned this round.
     *
     * @return 1 if it is the first round. Otherwise, (int) (round^2)*difficulty/2.
     */
    private int numberOfNewEnemies() {
        if (round == 1) {
            return 1;
        }
        return (int) (Math.pow(round, 2)*difficulty/2);
    }
}