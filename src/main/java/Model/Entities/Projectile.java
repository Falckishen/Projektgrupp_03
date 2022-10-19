package Model.Entities;

import Model.Direction;
import Model.EntityType;

/**
 * @author Ida Altenstedt
 */
class Projectile extends MovableEntity {

    private int lifeLeft;
    private final int attackPower;

    /**
     * Constructor for creating a projectile.
     * @param entityType The entity type for the projectile (what kind of projectile)
     * @param hitBoxRadiusX The radius on the X-axis
     * @param hitBoxRadiusY The radius on the Y-axis
     * @param x X coordinate
     * @param y Y coordinate
     * @param velocity The projectile's speed
     * @param piercingPower How many enemies the projectile can pierce in a row
     * @param direction the direction the projectile is moving
     * @param lifeLeft How many more frames it should be drawn before being considered dead.
     * @param attackPower The damage done on hit
     */
    Projectile(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y, int velocity,
                         int piercingPower, Direction direction, int lifeLeft, int attackPower) {
        super(entityType, hitBoxRadiusX, hitBoxRadiusY, x, y, velocity,piercingPower);
        setDirection(direction);
        this.lifeLeft = lifeLeft;
        this.attackPower = attackPower;
    }

    int getAttackPower() {
        return attackPower;
    }

    void CollidedWithEnemy(){
        takeDamage(1);
    }

    void collidedWithNonLivingObject(){
        setIsDead();
    }

    @Override
    public void doOnTick() {
        if(lifeLeft>0){
            move();
            lifeLeft -= 1;
        } else {
            setIsDead();
        }
    }
}