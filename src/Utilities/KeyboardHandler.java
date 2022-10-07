package Utilities;

import Controller.KeyboardInput;
import Model.Entities.Direction;

import java.util.*;

public class KeyboardHandler {
    private final List<Integer> keyBoardInputs;

    KeyboardHandler(List<Integer> keyInputs) {
        this.keyBoardInputs = keyInputs;
    }


    private void setNewDirection(Direction direction){
    }

    private Direction findDirection(List<Integer> currentDirections){
        Direction newDirection = Direction.DOWN;
        // FALL 1: Inga/alla knappar är nere
        if(currentDirections.isEmpty() || currentDirections.size() == 4) {
            return Direction.NONE;
        }
        // FALL 2: 1 Knapp är nere
        if(currentDirections.size() == 1) {
            setNewDirection(currentDirections.get(0));
            return newDirection;
        }
        // FALL 3: 2 Knappar är nere
        else if(currentDirections.size() == 2) {
            super.setSpeed(defaultSpeed);
            if(currentDirections.contains(Direction.UP) && currentDirections.contains(Direction.LEFT)) {
                super.setDirection(Direction.LEFT_UP);
            }
            else if (currentDirections.contains(Direction.UP) && currentDirections.contains((Direction.RIGHT))) {
                super.setDirection(Direction.RIGHT_UP);
            }
            else if(currentDirections.contains(Direction.DOWN) && currentDirections.contains(Direction.LEFT)) {
                super.setDirection(Direction.LEFT_DOWN);
            }
            else if (currentDirections.contains(Direction.DOWN) && currentDirections.contains((Direction.RIGHT))) {
                super.setDirection(Direction.RIGHT_DOWN);
            }
            else super.setSpeed(0);

        }
        // FALL 4: 3 knappar är nere
        else if(currentDirections.size() == 3) {
            super.setSpeed(defaultSpeed);
            if (currentDirections.contains(Direction.UP) && currentDirections.contains(Direction.DOWN)) {
                if(currentDirections.contains(Direction.LEFT)) super.setDirection(Direction.LEFT);
                else super.setDirection(Direction.RIGHT);
            }
            if (currentDirections.contains(Direction.LEFT) && currentDirections.contains(Direction.RIGHT)) {
                if(currentDirections.contains(Direction.UP)) super.setDirection(Direction.UP);
                else super.setDirection(Direction.DOWN);
            }
        }
    }
}
