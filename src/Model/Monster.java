package Model;

import Utilities.Direction;

public class Monster extends Entity{
    Monster(int coordX, int coordY, int hitboxWidthRadius, int hitboxHeightRadius) {
        super(coordX, coordY, hitboxWidthRadius, hitboxHeightRadius);

    }

    /* // Probably should be in factory
    Monster(int coordX, int coordY) {
        super(coordX, coordY, 20, 20);
    }*/

    // Stubb
    private Direction findClosestPlayerDirection() {
        return Direction.up;
    }

    @Override
    void move() {
        setDirection(findClosestPlayerDirection());
        int x = this.getCoordX();
        int y = this.getCoordY();
        switch (getDirection()){
            case up:
                y += this.getVelocity();
                break;
            case down:
                y -= this.getVelocity();
                break;
            case left:
                x -= this.getVelocity();
                break;
            case right:
                x += this.getVelocity();
                break;
        }
        setCoordX(x);
        setCoordY(y);
    }
}
