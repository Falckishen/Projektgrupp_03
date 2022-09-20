package Model;

import Model.Entities.EntityCreator;
import Model.Entities.Monster;

import java.util.ArrayList;

public class SpawnEnemies implements Runnable {

    private final Game game;
    private final EntityCreator entityCreator;
    private final int round;

    public SpawnEnemies(Game game, EntityCreator entityCreator, int round) {
        this.game = game;
        this.entityCreator = entityCreator;
        this.round = round;
    }

    @Override
    public void run() {
        for (int i = 0; i < round*2; i++) {
            entityCreator.createMonster(game.getPlayer());
        }

        game.enemiesHaveSpawned();
    }
}