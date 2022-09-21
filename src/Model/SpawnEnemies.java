package Model;

import Model.Entities.AddEnemy;

public class SpawnEnemies implements Runnable {

    private final Game game;
    private final AddEnemy entityCreatorEnemy;
    private final int round;

    public SpawnEnemies(Game game, AddEnemy entityCreatorEnemy, int round) {
        this.game = game;
        this.entityCreatorEnemy = entityCreatorEnemy;
        this.round = round;
    }

    @Override
    public void run() {
        // Number of entities = round-number^2
        for (int i = 0; i < Math.pow(round, 2); i++) {
            entityCreatorEnemy.createMonster();
        }
        game.enemiesHaveSpawned();
        System.out.println("ENEMIES SPAWNED: " + Math.pow(round, 2));
    }
}