package Model.Entities;

import Model.EntityType;

/**
 * @author Ida Altenstedt
 */
abstract class Friendly extends MovableEntity {

    private long lastTimeTakingDamage;

    Friendly(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y, int speed, int health) {
        super(entityType, hitBoxRadiusX, hitBoxRadiusY, x, y, speed, health);
        lastTimeTakingDamage = 0;
    }

    /**
     * This friendly takes damage if it was longer than 1000 ms since last time this friendly took damage.
     *
     * @param attackPower tha amount of damage the enemy "this" collided with
     * */
    void CollidedWithEnemy(int attackPower){
        // attackPower = how much damage self takes
        // System.currentTimeMillis() - lastTimeTakingDamage > invulnerability time
        int invulnerabilityTime = 500;
        if (System.currentTimeMillis() - lastTimeTakingDamage > invulnerabilityTime) {
            takeDamage(attackPower);
            lastTimeTakingDamage = System.currentTimeMillis();
        }
    }

}