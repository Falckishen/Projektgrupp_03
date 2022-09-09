package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.pow;

public class Monster extends Entity{
    Monster(int coordX, int coordY, int hitboxWidthRadius, int hitboxHeightRadius) {
        super(coordX, coordY, hitboxWidthRadius, hitboxHeightRadius);

    }

    private Position findClosestPosition(List<Position> positionList) {
        List<Double> playerDistances = new ArrayList<Double>();
        for (Position p : positionList){
            Double distance = Math.pow(p.getX() - this.getCurrentPosition().getX(), 2) +
                    Math.pow(p.getY() - this.getCurrentPosition().getY(),2);
            playerDistances.add(distance);
        }

        Double smallest = Collections.min(playerDistances);
        int indexOfSmallest = playerDistances.indexOf(smallest);


        return positionList.get(indexOfSmallest);
    }

    private Direction findDirectionToPosition(Position position) {
        
        return Direction.UP;
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
        switch (getDirection()){
            case UP:
                y += 1;
                break;
            case DOWN:
                y -= 1;
                break;
            case LEFT:
                x -= 1;
                break;
            case RIGHT:
                x += 1;
                break;
        }
        setCoordX(x);
        setCoordY(y);
    }

    @Override
    public void doOnTick() {
        move();
    }
}
