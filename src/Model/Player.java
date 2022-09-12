package Model;
import Utilities.Direction;

import java.util.ArrayList;

public class Player extends Entity {

    private ArrayList<Direction> listOfCurrentPlayerDirection;

    Player(int coordX, int coordY, int hitboxWidthRadius, int hitboxHeightRadius, ArrayList<Direction> listOfCurrentPlayerDirection) {
        super(coordX, coordY, hitboxWidthRadius, hitboxHeightRadius);
        this.listOfCurrentPlayerDirection = listOfCurrentPlayerDirection;
    }

    /*
    Player(int coordX, int coordY) {
        super(coordX, coordY, 20, 20);
    }
    */

    void move() {
        int x = this.getCurrentPosition().getX();
        int y = this.getCurrentPosition().getY();

        if(!listOfCurrentPlayerDirection.isEmpty()) {
            for (Direction direction : listOfCurrentPlayerDirection) {
                switch (direction) {
                    case UP -> y += 1;
                    case DOWN -> y -= 1;
                    case LEFT -> x -= 1;
                    case RIGHT -> x += 1;
                }
                setCoordX(x);
                setCoordY(y);
            }
        }
    }

    @Override
    public void doOnTick() {
        move();
    }
}
