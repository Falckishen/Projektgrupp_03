package Model;
import Utilities.Direction;
import Utilities.JustAPlaceToKeepThePublicList;

import java.util.HashMap;

public class Player extends Entity {
    private static HashMap<Integer, Entity> PLAYER = JustAPlaceToKeepThePublicList.ACTIVE_PLAYERS;

    Player(int coordX, int coordY, int hitboxWidthRadius, int hitboxHeightRadius) {
        super(coordX, coordY, hitboxWidthRadius, hitboxHeightRadius);
        addToHashMap();
    }

    @Override
    protected void addToHashMap() {
        PLAYER.put(this.getId(), this);
    }

    @Override
    protected void removeFromHashMap() {
        PLAYER.remove(this.getId());
    }

    /*
    Player(int coordX, int coordY) {
        super(coordX, coordY, 20, 20);
    }*/


    void move() {
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
