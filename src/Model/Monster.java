package Model;

import java.util.List;

public class Monster extends Entity{
    Monster(int coordX, int coordY, int hitboxWidthRadius, int hitboxHeightRadius) {
        super(coordX, coordY, hitboxWidthRadius, hitboxHeightRadius);

    }

    private Position findClosestPosition(List<Position> positionList) {
        return new Position(0,0);
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

    @Override
    void move() {
        // Position closePosition = findClosestPosition(listOfPlayers);
        // Direction goToDirection = findDirectionToPosition(closePosition);
        Direction goToDirection = Direction.UP;
        setDirection(goToDirection);
        int x = this.getCoordX();
        int y = this.getCoordY();
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
