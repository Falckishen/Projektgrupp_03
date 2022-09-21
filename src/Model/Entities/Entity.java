package Model.Entities;

import Model.OnTick;
import Utilities.Direction;
import Utilities.EntityType;

public abstract class Entity implements OnTick {
    //private final int id;
    private final EntityType entityType;
    private final int hitBoxRadiusX;
    private final int hitBoxRadiusY;

    private Position coordinate;
    private Direction direction;
    private int speed;
    private boolean isDead = false;

    protected Entity(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y, int speed){
        this.entityType = entityType;
        this.hitBoxRadiusX = hitBoxRadiusX;
        this.hitBoxRadiusY = hitBoxRadiusY;

        this.coordinate = new Position(x, y);
        this.direction = Direction.LEFT;
        this.speed = speed;
    }

    //protected int getId(){return id;}

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

    protected void move(){
        int diagSpeed = (int) (getSpeed()*Math.sqrt(2)/2);
        switch (direction) {
            case UP -> setY(getCurrentPosition().getY()-getSpeed());
            case LEFT_UP -> {
                setX(getCurrentPosition().getX()-diagSpeed);
                setY(getCurrentPosition().getY()-diagSpeed);
            }
            case RIGHT_UP -> {
                setX(getCurrentPosition().getX()+diagSpeed);
                setY(getCurrentPosition().getY()-diagSpeed);
            }
            case DOWN -> setY(getCurrentPosition().getY()+getSpeed());
            case LEFT_DOWN -> {
                setX(getCurrentPosition().getX()-diagSpeed);
                setY(getCurrentPosition().getY()+diagSpeed);
            }
            case RIGHT_DOWN -> {
                setX(getCurrentPosition().getX()+diagSpeed);
                setY(getCurrentPosition().getY()+diagSpeed);
            }
            case LEFT -> setX(getCurrentPosition().getX()-getSpeed());
            case RIGHT -> setX(getCurrentPosition().getX()+getSpeed());
        }
    }
}