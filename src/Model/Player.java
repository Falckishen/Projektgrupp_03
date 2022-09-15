package Model;

import java.util.HashMap;
import java.util.ArrayList;
import Utilities.Direction;
import Utilities.JustAPlaceToKeepThePublicList;

public class Player extends Entity {
    private final HashMap<Integer, Entity> player;
    private final ArrayList<Direction> currentPlayerWalkingDirection;

    Player(int x, int y, ArrayList<Direction> currentPlayerWalkingDirection) {
        super(x, y, 25, 25, 5);
        this.player = JustAPlaceToKeepThePublicList.ACTIVE_PLAYERS;
        this.currentPlayerWalkingDirection = currentPlayerWalkingDirection;
        addToHashMap();
    }

    @Override
    protected void addToHashMap() {
        player.put(super.getId(), this);
    }

    @Override
    protected void removeFromHashMap() {
        player.remove(super.getId());
    }

    @Override
    public void doOnTick() {
        move();
    }

    private void move() {
        if(!currentPlayerWalkingDirection.isEmpty()) {
            for (Direction direction : currentPlayerWalkingDirection) {
                switch (direction) {
                    case UP -> super.setY(super.getY()-super.getSpeed());
                    case DOWN -> super.setY(super.getY()+super.getSpeed());
                    case LEFT -> super.setX(super.getX()-super.getSpeed());
                    case RIGHT -> super.setX(super.getX()+super.getSpeed());
                }
            }
        }
    }
}
