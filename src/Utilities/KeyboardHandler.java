package Utilities;

import Controller.KeyboardInput;
import Model.Entities.Direction;

import java.util.*;

public class KeyboardHandler {
    /*private List<Integer> keyInputs;

    KeyboardHandler(List<Integer> keyInputs) {
        this.keyBoardInputs = keyInputs;
    }


    private void setNewDirection(Direction direction){
    }*/

    public static Direction findDirection(List<Direction> currentDirections){
        Direction newDirection = Direction.DOWN;


        // FALL 1: 1 Knapp är nere
        if(currentDirections.size() == 1) {
            return currentDirections.get(0);
        }
        // FALL 2: 2 Knappar är nere
        else if(currentDirections.size() == 2) {
            if(currentDirections.contains(Direction.UP) && currentDirections.contains(Direction.LEFT)) {
                return Direction.LEFT_UP;
            }
            else if (currentDirections.contains(Direction.UP) && currentDirections.contains((Direction.RIGHT))) {
                return Direction.RIGHT_UP;
            }
            else if(currentDirections.contains(Direction.DOWN) && currentDirections.contains(Direction.LEFT)) {
                return Direction.LEFT_DOWN;
            }
            else if (currentDirections.contains(Direction.DOWN) && currentDirections.contains((Direction.RIGHT))) {
                return Direction.RIGHT_DOWN;
            }
            else return Direction.NONE;

        }
        // FALL 3: 3 knappar är nere
        else if(currentDirections.size() == 3) {
            if (currentDirections.contains(Direction.UP) && currentDirections.contains(Direction.DOWN)) {
                if(currentDirections.contains(Direction.LEFT)) return Direction.LEFT;
                else return Direction.RIGHT;
            }
            if (currentDirections.contains(Direction.LEFT) && currentDirections.contains(Direction.RIGHT)) {
                if(currentDirections.contains(Direction.UP)) return Direction.UP;
                else return Direction.DOWN;
            }
        }
        // FALL 4: Inga/alla knappar är nere
        // (currentDirections.isEmpty() || currentDirections.size() == 4)
        return Direction.NONE;
    }
}
