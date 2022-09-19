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

    private void move() {
        if(!currentPlayerWalkingDirection.isEmpty()) {
            for (Direction direction : currentPlayerWalkingDirection) {
                switch (direction) {
                    case UP -> this.setY(super.getCurrentPosition().getY()-super.getSpeed());
                 //   case LEFTUP -> this.setY()
                    case DOWN -> super.setY(super.getCurrentPosition().getY()+super.getSpeed());
                    case LEFT -> super.setX(super.getCurrentPosition().getX()-super.getSpeed());
                    case RIGHT -> super.setX(super.getCurrentPosition().getX()+super.getSpeed());
                }
            }
        }
    }
}