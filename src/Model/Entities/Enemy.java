package Model.Entities;

import java.util.*;

import Utilities.Direction;
import Utilities.EntityType;
import Utilities.Position;

abstract class Enemy extends MovableEntity {
    private final int attackPower;
    private final Iterable<Friendly> friendliesIterator;
    //private Position closestFriendlyPosition;

    protected Enemy(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y, int speed, int health, int attackPower, Iterable<Friendly> friendliesIterator) {
        super(entityType, hitBoxRadiusX, hitBoxRadiusY, x, y, speed, health);
        this.attackPower = attackPower;
        this.friendliesIterator = friendliesIterator;
    }

    protected int getAttackPower() {
        return attackPower;
    }

    protected Iterable<Friendly> getFriendliesIterator() {
        return friendliesIterator;
    }

    // FOR MULTIPLAYER// (lists)
    protected Position findClosestPosition(List<Position> positionList) {
        List<Double> playerDistances = new ArrayList<>();
        for (Position p : positionList){
            Double distance = Math.pow(p.getX() - this.getPosition().getX(), 2) +
                    Math.pow(p.getY() - this.getPosition().getY(),2);
            playerDistances.add(distance);
        }
        Double smallest = Collections.min(playerDistances);
        int indexOfSmallest = playerDistances.indexOf(smallest);

        return positionList.get(indexOfSmallest);
    }

    protected void collidedWithFriendly(Position friendlyPosition){
        System.out.println("collided Player Monster");
        //knocked back from player
        move(-20);
    }

    protected void collidedWIthEnemy(Iterator<Position> enemyPositions) {
        Random rand = new Random();
        double nextDeltaX;
        double nextDeltaY;

        double deltaX = 0;
        double deltaY = 0;
        double v;

        while(enemyPositions.hasNext()){
            Position nextEnemyPosition = enemyPositions.next();

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

    protected void collidedWithProjectile(int attackPower){
        //looses health in relation to the attackPower
        takeDamage(attackPower);
    }

    protected void collidedWithNonLivingObject(Entity object){
        //can't move in this direction
        //gives small knock back so Enemy doesn't get stuck in wall

        move(-2);

    }
}