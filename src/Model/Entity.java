package Model;

import Utilities.Direction;

public abstract class Entity {
    private int id;
    private Position coordinate;
    private int hitboxWidthRadius;
    private int hitboxHeightRadius;
    private Direction direction;
    private int velocity;
    private int health;


    Entity(int coordX, int coordY, int hitboxWidthRadius, int hitboxHeightRadius){
        this.coordinate = new Position(coordX, coordY);
        this.hitboxWidthRadius = hitboxWidthRadius;
        this.hitboxHeightRadius = hitboxHeightRadius;

        this.direction = Direction.down;
        this.velocity = 0;
        this.health = 1;
    }

    int getCoordX() {
        return coordinate.getX();
    }

    int getCoordY() {
        return coordinate.getY();
    }

    Direction getDirection() {
        return direction;
    }

    void setDirection(Direction direction) {
        this.direction = direction;
    }

    int getVelocity() {return velocity;}

    protected void setCoordX(int coordX) {
        this.coordinate.setX(coordX);
    }

    protected void setCoordY(int coordY) {
        this.coordinate.setY(coordY);
    }

    abstract void move();
}
