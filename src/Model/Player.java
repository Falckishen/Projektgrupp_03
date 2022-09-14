package Model;

import java.util.HashMap;
import java.util.ArrayList;
import Utilities.Direction;
import Utilities.JustAPlaceToKeepThePublicList;

public class Player extends Entity {
    private final HashMap<Integer, Entity> player;
    private final ArrayList<Direction> currentPlayerDirection;
    private int speed;

    Player(int x, int y, int hitBoxWidthRadius, int hitBoxHeightRadius, ArrayList<Direction> currentPlayerDirection) {
        super(x, y, hitBoxWidthRadius, hitBoxHeightRadius);
        this.player = JustAPlaceToKeepThePublicList.ACTIVE_PLAYERS;
        this.currentPlayerDirection = currentPlayerDirection;
        this.speed = 5;
        addToHashMap();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    protected void addToHashMap() {
        player.put(this.getId(), this);
    }

    @Override
    protected void removeFromHashMap() {
        player.remove(this.getId());
    }

    @Override
    public void doOnTick() {
        move();
    }

    private void move() {
        if(!currentPlayerDirection.isEmpty()) {
            for (Direction direction : currentPlayerDirection) {
                switch (direction) {
                    case UP -> super.setCoordY(super.getY()-speed);
                    case DOWN -> super.setCoordY(super.getY()+speed);
                    case LEFT -> super.setCoordX(super.getX()-speed);
                    case RIGHT -> super.setCoordX(super.getX()+speed);
                }
            }
        }
        System.out.println(getX() + " " + getY());
    }
}
