package Model.Entities;

import Utilities.Direction;
import Utilities.EntityType;

import java.util.ArrayList;
import java.util.Iterator;

class Monster extends Enemy {

    Monster(int hitBoxWidthRadius, int hitBoxHeightRadius, int x, int y, int speed, int attackPower, int attackRange, Iterable<Friendly> friendliesIterator) {
        super(EntityType.monster, hitBoxWidthRadius, hitBoxHeightRadius, x, y, speed, attackPower, attackRange, friendliesIterator);
    }

    Monster(int x, int y, Iterable<Friendly> friendliesIterator) {
        super(EntityType.monster, 10, 10, x, y, 5, 1, 5, friendliesIterator);
    }

    /**
     * Makes it so that a monster changes direction according to where the player is
     * @param p
     * @return a direction towards Position p
     */
    public Direction findDirectionToPosition(Position p) {
        int vx = p.getX() - this.getCurrentPosition().getX();
        int vy = p.getY() - this.getCurrentPosition().getY();
        // Top is 0 degrees
        int ux = 0;
        int uy = 1;

        int num = (vx*ux + vy*uy);
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
        int x = this.getCurrentPosition().getX();
        int y = this.getCurrentPosition().getY();
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
        while(friendlyIterator.hasNext()){
            positionsOfFriendlies.add(friendlyIterator.next().getCurrentPosition());
        }
        this.setDirection(findDirectionToPosition(findClosestPosition(positionsOfFriendlies)));

        move();
    }
}
