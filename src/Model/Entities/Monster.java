package Model.Entities;

import java.util.ArrayList;
import java.util.Iterator;
import Utilities.EntityType;
import Utilities.Position;

/**
 * @author Ida Altenstedt
 */
class Monster extends Enemy {

    Monster(int x, int y, Iterable<Friendly> friendliesIterator, int speed, int health, int attackPower){
        super(EntityType.monster, 50, 50, x, y, speed, health, attackPower,
                friendliesIterator);
    }

    /**
     * Makes it so that a monster changes direction according to where the player is
     * @param p
     * @return a direction towards Position p
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
    /* // Probably should be in factory
    Monster(int coordX, int coordY) {
        super(coordX, coordY, 20, 20);
    }*/

    // Stubb
    private Direction findClosestPlayerDirection() {
        return Direction.UP;
    }

    /*    void move() {
        // Position closePosition = findClosestPosition(listOfPlayers);
        // Direction goToDirection = findDirectionToPosition(closePosition);
        Direction goToDirection = Direction.UP;
        setDirection(goToDirection);
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        switch (getDirection()) {
            case UP -> y += 1;
            case DOWN -> y -= 1;
            case LEFT -> x -= 1;
            case RIGHT -> x += 1;
        }
        setX(x);
        setY(y);
    }*/

    private void attack() {

    }

    @Override
    public void doOnTick() {
        // findClosestPosition()
        // attack();
        Iterator<Friendly> friendlyIterator = getFriendliesIterator().iterator();
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
}