package Model.Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Utilities.EntityType;
import Utilities.Position;

abstract class Enemy extends Entity {
    private final int attackPower;
    private final int attackRange;
    private final Iterable<Friendly> friendliesIterator;
    //private Position closestFriendlyPosition;

    protected Enemy(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y, int speed, int attackPower, int attackRange, Iterable<Friendly> friendliesIterator) {
        super(entityType, hitBoxRadiusX, hitBoxRadiusY, x, y, speed);
        this.attackPower = attackPower;
        this.attackRange = attackRange;
        this.friendliesIterator = friendliesIterator;
    }

    protected int getAttackPower() {
        return attackPower;
    }

    protected int getAttackRange() {
        return attackRange;
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
        int diagSpeed = (int) (getSpeed()*Math.sqrt(2)/2);
        switch (getDirection()) {
            case DOWN -> getPosition().setY(getPosition().getY()-getSpeed()*10);
            case RIGHT_DOWN -> {
                getPosition().setX(getPosition().getX()-diagSpeed*10);
                getPosition().setY(getPosition().getY()-diagSpeed*10);
            }
            case LEFT_DOWN -> {
                getPosition().setX(getPosition().getX()+diagSpeed*10);
                getPosition().setY(getPosition().getY()-diagSpeed*10);
            }
            case UP -> getPosition().setY(getPosition().getY()+getSpeed()*10);
            case RIGHT_UP -> {
                getPosition().setX(getPosition().getX()-diagSpeed*10);
                getPosition().setY(getPosition().getY()+diagSpeed*10);
            }
            case LEFT_UP -> {
                getPosition().setX(getPosition().getX()+diagSpeed*10);
                getPosition().setY(getPosition().getY()+diagSpeed*10);
            }
            case RIGHT -> getPosition().setX(getPosition().getX()-getSpeed()*10);
            case LEFT -> getPosition().setX(getPosition().getX()+getSpeed()*10);
        }
    }

    protected void collidedWIthEnemy(Position enemyPosition){
        //TODO position needed together with self's direction to know which enemy walked into which
    }

    protected void collidedWithProjectile(int attackPower){
        setIsDead(true);
        //TODO looses health in relation to the attackPower
    }

    protected void collidedWithNonLivingObject(AllObjects object){
        //can't move in this direction
        //gives small knock back so enemy doesn't get stuck in wall

        int diagSpeed = (int) (getSpeed()*Math.sqrt(2)/2);
        switch (getDirection()) {
            case DOWN -> getPosition().setY(getPosition().getY()-getSpeed()*2);
            case RIGHT_DOWN -> {
                getPosition().setX(getPosition().getX()-diagSpeed*2);
                getPosition().setY(getPosition().getY()-diagSpeed*2);
            }
            case LEFT_DOWN -> {
                getPosition().setX(getPosition().getX()+diagSpeed*2);
                getPosition().setY(getPosition().getY()-diagSpeed*2);
            }
            case UP -> getPosition().setY(getPosition().getY()+getSpeed()*2);
            case RIGHT_UP -> {
                getPosition().setX(getPosition().getX()-diagSpeed*2);
                getPosition().setY(getPosition().getY()+diagSpeed*2);
            }
            case LEFT_UP -> {
                getPosition().setX(getPosition().getX()+diagSpeed*2);
                getPosition().setY(getPosition().getY()+diagSpeed*2);
            }
            case RIGHT -> getPosition().setX(getPosition().getX()-getSpeed()*2);
            case LEFT -> getPosition().setX(getPosition().getX()+getSpeed()*2);
        }

    }
}