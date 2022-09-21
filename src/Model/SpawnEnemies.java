package Model;

import Model.Entities.EntityCreator;

class SpawnEnemies implements Runnable {

    private final Game game;
    private final EntityCreator entityCreator;
    private final int round;

    SpawnEnemies(Game game, EntityCreator entityCreator, int round) {
        this.game = game;
        this.entityCreator = entityCreator;
        this.round = round;
    }

    @Override
    public void run() {
        for (int i = 0; i < Math.pow(round, 2); i++) {
            entityCreator.createMonster(game.getPlayer());
        }
        game.enemiesHaveSpawned();
        System.out.println("ENEMIES SPAWNED: " + Math.pow(round, 2));
    }
}