package Model.Entities;

import Utilities.EntityType;
import Utilities.Position;

import java.util.Iterator;
import java.util.Random;

/**
 * @author Ida Altenstedt
 */
abstract class Friendly extends MovableEntity {

    protected Friendly(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y,
                       int speed, int health) {
        super(entityType, hitBoxRadiusX, hitBoxRadiusY, x, y, speed, health);
    }

    protected void CollidedWithEnemy(int attackPower){
        // attackPower = how much damage self takes
        takeDamage(attackPower);
        //TODO invulnerability frames

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