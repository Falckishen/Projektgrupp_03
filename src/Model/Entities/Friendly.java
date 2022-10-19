package Model.Entities;

/**
 * @author Ida Altenstedt
 */
abstract class Friendly extends MovableEntity {

    private long lastTimeTookDamage;

    protected Friendly(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y, int speed, int health) {
        super(entityType, hitBoxRadiusX, hitBoxRadiusY, x, y, speed, health);
        lastTimeTookDamage = 0;
    }

    /**
     * This friendly takes damage if it was longer than 1000 ms since last time this friendly took damage.
     *
     * @param attackPower tha amount of damage the enemy "this" collided with
     * */
    protected void CollidedWithEnemy(int attackPower){
        // attackPower = how much damage self takes
        // System.currentTimeMillis() - lastTimeTookDamage > invulnerability time
        int invulnerabilityTime = 500;
        if (System.currentTimeMillis() - lastTimeTookDamage > invulnerabilityTime) {
            takeDamage(attackPower);
            lastTimeTookDamage = System.currentTimeMillis();
        }
    }
}