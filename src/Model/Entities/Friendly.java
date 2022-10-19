package Model.Entities;

import java.util.Iterator;
import java.util.Random;
import Utilities.EntityType;
import Utilities.Position;

/**
 * @author Ida Altenstedt
 */
abstract class Friendly extends MovableEntity {

    private long lastTimeTakingDamage;

    protected Friendly(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y, int speed, int health) {
        super(entityType, hitBoxRadiusX, hitBoxRadiusY, x, y, speed, health);
        lastTimeTakingDamage = 0;
    }

    /**
     * This friendly takes damage if it was longer than 1000 ms since last time this friendly took damage.
     *
     * @param attackPower tha amount of damage the enemy "this" collided with
     * */
    protected void CollidedWithEnemy(int attackPower){
        // attackPower = how much damage self takes
        // System.currentTimeMillis() - lastTimeTakingDamage > invulnerability time
        if (System.currentTimeMillis() - lastTimeTakingDamage > 1000) {
            takeDamage(attackPower);
            lastTimeTakingDamage = System.currentTimeMillis();
        }
    }

}