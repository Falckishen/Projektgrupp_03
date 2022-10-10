package Model.Entities;

import java.util.*;

import Utilities.EntityType;
import Utilities.Position;

/**
 * The abstract class Enemy containing the code used by all hostile entities.
 * @author Ida Altenstedt
 */
abstract class Enemy extends MovableEntity {
    /**
     * The attack power for the specific enemy.
     */
    private final int attackPower;
    /**
     * a list over all friendly entities in play.
     * <p>useful when enemies figure out which direction to walk</p>
     */
    private final Iterable<Friendly> friendliesIterable;

    /**
     * The constructor for an Enemy.
     * @param entityType which type of enemy it is that is being made.
     * @param hitBoxRadiusX the enemy's hitbox radius on the x-axis.
     * @param hitBoxRadiusY the enemy's hitbox radius on the y-axis
     * @param x position on x-axis.
     * @param y position on y-axis.
     * @param speed The speed this Enemy will use.
     * @param health The health of the enemy.
     * @param attackPower The attack power of the enemy.
     * @param friendliesIterable an alias of the list of friendly entities (saved as Iterable)
     */
    protected Enemy(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y, int speed, int health, int attackPower, Iterable<Friendly> friendliesIterable) {
        super(entityType, hitBoxRadiusX, hitBoxRadiusY, x, y, speed, health);
        this.attackPower = attackPower;
        this.friendliesIterable = friendliesIterable;
    }

    /**
     * A get method for the attack power.
     * @return attack power.
     */
    protected int getAttackPower() {
        return attackPower;
    }

    /**
     * Walks towards the closest friendly entity of such exists.
     */
    protected void walkTowardsFriendly(){
        Iterator<Friendly> friendlyIterator = friendliesIterable.iterator();
        ArrayList<Position> positionsOfFriendlies = new ArrayList<>();
        if (friendlyIterator.hasNext()) {
            while (friendlyIterator.hasNext()) {
                positionsOfFriendlies.add(friendlyIterator.next().getPosition());
            }
            Position p;
            try {
                p = findClosestPosition(positionsOfFriendlies);
            } catch (NullPointerException e){
                p = new Position(0,0);
            }
            this.setDirection(findDirectionToPosition(p));

            move();
        }
    }

    /**
     * Find which position in a list is the closest to me.
     * @param positionList The list containing the positions to be checked.
     * @return Which position is closest.
     */
    private Position findClosestPosition(List<Position> positionList) {
        List<Double> playerDistances = new ArrayList<>();
        for (Position p : positionList){
            Double distance = Math.pow(p.getX() - this.getPosition().getX(), 2) +
                    Math.pow(p.getY() - this.getPosition().getY(),2);
            playerDistances.add(distance);
        }

        if (playerDistances.isEmpty()){return null;}

        Double smallest = Collections.min(playerDistances);
        int indexOfSmallest = playerDistances.indexOf(smallest);

        return positionList.get(indexOfSmallest);
    }

    /**
     * Finds which one of the eight directions are the most useful to get to the desired position.
     * @param p The position we want to move towards.
     * @return The direction we will be moving.
     */
    private Direction findDirectionToPosition(Position p) {
        int vx = p.getX() - this.getPosition().getX();
        int vy = this.getPosition().getY() - p.getY();
        // Top is 0 degrees
        int ux = 0;
        int uy = 1;

        int num = vy * uy;
        double den = (Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2)) * (Math.sqrt(Math.pow(ux, 2) + Math.pow(uy, 2))) );
        // To avoid division by 0
        if (den == 0) {
            return Direction.UP;
        }
        double cos =  num / den;
        double angle = Math.acos(cos);


        if (angle < Math.PI / 8) {
            return Direction.UP;
        }

        if (vx >= 0) {
            if (angle < 3 * Math.PI / 8) return Direction.RIGHT_UP;
            if (angle < 5 * Math.PI / 8) return Direction.RIGHT;
            if (angle < 7 * Math.PI / 8) return Direction.RIGHT_DOWN;
        } else {
            if (angle < 3 * Math.PI / 8) return Direction.LEFT_UP;
            if (angle < 5 * Math.PI / 8) return Direction.LEFT;
            if (angle < 7 * Math.PI / 8) return Direction.LEFT_DOWN;
        }
        return Direction.DOWN;
    }

    /*------------------------------------------------ Collisions ----------------------------------------------------*/

    protected void collidedWithProjectile(int attackPower){
        //looses health in relation to the attackPower
        takeDamage(attackPower);
    }

    protected void collidedWithFriendly(){
        //pushed back
        move(-2);
    }

    protected void collidedWithEnemy(Iterator<Position> collidedPositions){
        //TODO weird thing with half stuck in wall. FIX!
        //TODO doublet exists in Friendly
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

            deltaX += 100/nextDeltaX;
            deltaY += 100/nextDeltaY;
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

    protected void collidedWithNonLiving(Iterator<Position> collidedPositions){
        //TODO weird thing with half stuck in wall. FIX!
        //TODO doublet exists in Friendly
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

            deltaX += 100/nextDeltaX;
            deltaY += 100/nextDeltaY;
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