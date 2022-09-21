package Model.Entities;

import Utilities.EntityType;
import Utilities.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class Enemy extends Entity {
    private final int attackPower;
    private final int attackRange;
    private Iterable<Friendly> friendliesIterator;
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
        //TODO knocked back from player
    }

    protected void collidedWIthEnemy(Position enemyPosition){
        //TODO position needed together with self's direction to know which enemy walked into which
    }

    protected void collidedWithProjectile(int attackPower){
        //TODO looses health in relation to the attackPower
    }

    protected void collidedWithNonLivingObject(AllObjects object){
        //TODO can't move in this direction
    }
}
