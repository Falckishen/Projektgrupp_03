package Model;

import Model.Entities.EntityCreator;
import Model.Entities.Monster;

import java.util.ArrayList;

public class SpawnEnemies implements Runnable {

    private final Game game;
    private final EntityCreator entityCreator;
    private int round;
    private final ArrayList<Monster> monstersAlive;

    public SpawnEnemies(Game game, EntityCreator entityCreator, int round, ArrayList<Monster> monstersAlive) {
        this.game = game;
        this.entityCreator = entityCreator;
        this.round = round;
        this.monstersAlive = monstersAlive;
    }

    @Override
    public void run() {
        // TODO finish monster-spawning
        //monstersAlive.add(entityCreator.createMonster(50, 50, 10, 10, 5));

        game.enemiesHaveSpawned();
    }
}