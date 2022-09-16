package Model.Entities;

import java.util.List;

import Utilities.Direction;

public class Player extends Friendly {
    private final List<Direction> currentPlayerWalkingDirection;

    Player(int x, int y, List<Direction> currentPlayerWalkingDirection) {
        super(x, y, 25, 25, 5);
        this.currentPlayerWalkingDirection = currentPlayerWalkingDirection;
    }

    @Override
    public void doOnTick() {
        move();
    }

    private void move() {
        if(!currentPlayerWalkingDirection.isEmpty()) {
            for (Direction direction : currentPlayerWalkingDirection) {
                switch (direction) {
                    case UP -> super.setY(super.getCurrentPosition().getY()-super.getSpeed());
                    case DOWN -> super.setY(super.getCurrentPosition().getY()+super.getSpeed());
                    case LEFT -> super.setX(super.getCurrentPosition().getX()-super.getSpeed());
                    case RIGHT -> super.setX(super.getCurrentPosition().getX()+super.getSpeed());
                }
            }
        }
    }
}
