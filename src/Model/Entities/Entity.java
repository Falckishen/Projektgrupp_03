package Model.Entities;

import Model.OnTick;
import Utilities.Direction;

public abstract class Entity implements OnTick {
    private int id;
    private Position coordinate;
    private int hitBoxRadiusX;
    private int hitBoxRadiusY;
    private Direction direction;
    private int speed;
    private boolean isDead = false;

    protected Entity(int x, int y, int hitBoxRadiusX, int hitBoxRadiusY, int speed){
        this.coordinate = new Position(x, y);
        this.hitBoxRadiusX = hitBoxRadiusX;
        this.hitBoxRadiusY = hitBoxRadiusY;
        this.direction = Direction.LEFT;
        this.speed = speed;
    }

    protected int getId(){
        return id;
    }

    public Position getCurrentPosition() {
        return coordinate;
    }

    protected Direction getDirection() {
        return direction;
    }

    protected int getSpeed() {
        return speed;
    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    protected void setSpeed(int speed) {
        this.speed = speed;
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

    protected void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    protected boolean getIsDead() {
        return isDead;
    }
}