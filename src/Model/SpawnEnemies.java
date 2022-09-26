package Model;

import Model.Entities.AddEnemy;

class SpawnEnemies implements Runnable {

    private final Game game;
    private final AddEnemy enemyEntityCreator;
    private final int round;
    private final int difficulty;

    SpawnEnemies(Game game, AddEnemy enemyEntityCreator, int round, int difficulty) {
        this.game = game;
        this.enemyEntityCreator = enemyEntityCreator;
        this.round = round;
        this.difficulty = difficulty;
    }

    @Override
    public void run() {
        int numberOfNewEnemies = numberOfNewEnemies();
        for (int i = 0; i < numberOfNewEnemies; i++) {
            enemyEntityCreator.createMonster();
        }
        game.enemiesHaveSpawned();
        System.out.println("ENEMIES SPAWNED: " + numberOfNewEnemies); // Debug
    }

    private int numberOfNewEnemies() {
        return (int) (Math.pow(round, 2) * difficulty);
    }
}