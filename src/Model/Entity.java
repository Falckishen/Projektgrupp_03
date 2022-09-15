package Model;

import Utilities.Direction;

public abstract class Entity implements OnTick{
    private int id;
    private Position coordinate;
    private int hitBoxRadiusX;
    private int hitBoxRadiusY;
    private Direction direction;
    private int velocity;


    protected Entity(int x, int y, int hitBoxRadiusX, int hitBoxRadiusY){
        this.coordinate = new Position(x, y);
        this.hitBoxRadiusX = hitBoxRadiusX;
        this.hitBoxRadiusY = hitBoxRadiusY;
        this.direction = Direction.LEFT;
        this.velocity = 10;
    }

    protected int getId(){
        return id;
    }

    protected Position getCurrentPosition() {
        return coordinate;
    }

    protected int getX() {
        return coordinate.getX();
    }
    protected int getY() {
        return coordinate.getY();
    }

    protected Direction getDirection() {
        return direction;
    }

    protected int getVelocity() {
        return velocity;
    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    protected void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    protected void setX(int x) {
        this.coordinate.setX(x);
    }

    protected void setY(int y) {
        this.coordinate.setY(y);
    }

    protected int getHitBoxRadiusX() {
        return hitBoxRadiusX;
    }

    protected int getHitBoxRadiusY() {
        return hitBoxRadiusY;
    }

    protected abstract void addToHashMap();
    protected abstract void removeFromHashMap();
}
