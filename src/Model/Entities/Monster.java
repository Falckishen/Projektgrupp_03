package Model.Entities;

import Utilities.JustAPlaceToKeepThePublicList;
import Utilities.Direction;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monster extends Enemy {

    Monster(int x, int y, int hitBoxWidthRadius, int hitBoxHeightRadius, int speed) {
        super(x, y, hitBoxWidthRadius, hitBoxHeightRadius, speed);
    }

    private Position findClosestPosition(List<Position> positionList) {
        List<Double> playerDistances = new ArrayList<>();
        for (Position p : positionList){
            Double distance = Math.pow(p.getX() - this.getCurrentPosition().getX(), 2) +
                    Math.pow(p.getY() - this.getCurrentPosition().getY(),2);
            playerDistances.add(distance);
        }
        Double smallest = Collections.min(playerDistances);
        int indexOfSmallest = playerDistances.indexOf(smallest);

        return positionList.get(indexOfSmallest);
    }

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

        if (angle < Math.PI / 4) {
            return Direction.UP;
        }
        if (vx >= 0) {
            if (angle < 3 * Math.PI / 4 ) {
                return Direction.RIGHT;
            }
        } else {
            if (angle < 3 * Math.PI / 4 ) {
                return Direction.LEFT;
            }
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

    void move() {
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
    }

    @Override
    public void doOnTick() {
        // findClosestPosition()
        move();
    }
}
