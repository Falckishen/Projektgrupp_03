package Model.Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        setIsDead(true);
        move(-5);
    }

   /* protected void collidedWIthEnemy(Position enemyPosition) {
        double deltaX = getPosition().getX() - enemyPosition.getX();
        double deltaY = getPosition().getY() - enemyPosition.getY();

        double v = Math.atan(deltaY / deltaX);
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

    }*/

    protected void collidedWIthEnemy(Position enemyPosition){
        //position needed together with self's direction to know which enemy walked into which

        boolean iWalkedIntoYou = false;
        if (getDirection() == Direction.LEFT){
            if (enemyIsToTheLeft(enemyPosition)){iWalkedIntoYou = true;}
        }
        else if (getDirection() == Direction.RIGHT){
            if (enemyIsToTheRight(enemyPosition)){iWalkedIntoYou = true;}
        }
        else if (getDirection() == Direction.UP){
            if (enemyIsAbove(enemyPosition)){iWalkedIntoYou = true;}
        }
        else if (getDirection() == Direction.DOWN){
            if (enemyIsBelow(enemyPosition)){iWalkedIntoYou = true;}
        }

        else if (getDirection() == Direction.LEFT_DOWN){
            if (enemyIsToTheLeft(enemyPosition) && enemyIsBelow(enemyPosition)){iWalkedIntoYou = true;}
        }
        else if (getDirection() == Direction.LEFT_UP){
            if (enemyIsToTheLeft(enemyPosition) && enemyIsAbove(enemyPosition)){iWalkedIntoYou = true;}
        }
        else if (getDirection() == Direction.RIGHT_DOWN){
            if (enemyIsToTheRight(enemyPosition) && enemyIsBelow(enemyPosition)){iWalkedIntoYou = true;}
        }
        else if (getDirection() == Direction.RIGHT_UP){
            if (enemyIsToTheRight(enemyPosition) && enemyIsAbove(enemyPosition)){iWalkedIntoYou = true;}
        }

        if (iWalkedIntoYou){
            move(-3);
        }
    }

    private boolean enemyIsAbove(Position enemyPosition){return enemyPosition.getY() >= getPosition().getY();}
    private boolean enemyIsBelow(Position enemyPosition){return enemyPosition.getY() <= getPosition().getY();}
    private boolean enemyIsToTheLeft(Position enemyPosition){return enemyPosition.getX() <= getPosition().getX();}
    private boolean enemyIsToTheRight(Position enemyPosition){return enemyPosition.getX() >= getPosition().getX();}


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