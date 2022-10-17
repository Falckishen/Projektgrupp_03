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
        if (System.currentTimeMillis() - lastTimeTakingDamage > 500) {
            takeDamage(attackPower);
            lastTimeTakingDamage = System.currentTimeMillis();
        }
    }

    protected void collidedWithNonLivingObject(Entity object){
        //can't move in this direction
        //gives small knock back so Friendly doesn't get stuck in wall
        move(-2);
    }

    protected void collidedWithNotProjectile(Iterator<Position> collidedPositions){
        //TODO weird thing with half stuck in wall. FIX!
        //TODO doublet exists in Enemy
        Random rand = new Random();
        double nextDeltaX;
        double nextDeltaY;

        double deltaX = 0;
        double deltaY = 0;
        double v;

        while(collidedPositions.hasNext()){
            Position nextEnemyPosition = collidedPositions.next();

            if (getPosition().getX() == nextEnemyPosition.getX() && nextEnemyPosition.getY() == getPosition().getY()){
                double tempV = rand.nextInt(360);
                nextDeltaX = Math.cos(tempV) * 2;
                nextDeltaY = Math.sin(tempV) * 2;
            } else {
                nextDeltaX = getPosition().getX() - nextEnemyPosition.getX();
                nextDeltaY = getPosition().getY() - nextEnemyPosition.getY();
            }

            deltaX += 10/nextDeltaX;
            deltaY += 10/nextDeltaY;
        }

        if (deltaX == 0){
            deltaX = 0.01;
        }
        v = Math.atan(deltaY / deltaX);
        double moveX = Math.cos(v) * getSpeed();
        if (deltaX < 0) { //negative
            moveX = moveX * (-1);
        }
        double moveY = Math.sin(v) * getSpeed();
        if (deltaY < 0) { //negative
            moveY = moveY * (-1);
        }

        getPosition().setX(getPosition().getX() + (int) moveX);
        getPosition().setY(getPosition().getY() + (int) moveY);
    }
}