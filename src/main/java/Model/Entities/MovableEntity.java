package Model.Entities;

import Model.Direction;
import Model.EntityType;
import Model.OnTick;

/**
 * @author Ida Altenstedt
 */
public abstract class MovableEntity extends Entity implements OnTick {

    private Direction direction;
    private int speed;
    private int health;

    MovableEntity(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y,
                            int speed, int health){
        super(entityType, hitBoxRadiusX, hitBoxRadiusY, x, y);
        this.direction = Direction.LEFT;
        this.speed = speed;
        this.health= health;
    }

    Direction getDirection() {
        return direction;
    }

    int getSpeed() {
        return speed;
    }

    void setDirection(Direction direction) {
        this.direction = direction;
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }

    void takeDamage(int attackPower){
        health -= attackPower;
        if (health <= 0){
            setIsDead();
        }
    }

    public int getHealth() {
        return health;
    }


    void move(){
        move(1);
    }

    void move(int multiplier){ //Used in collision
        int diagSpeed = (int) (getSpeed()*Math.sqrt(2)/2);
        switch (direction) {
            case UP -> getPosition().setY(getPosition().getY()-getSpeed()*multiplier);
            case LEFT_UP -> {
                getPosition().setX(getPosition().getX()-diagSpeed*multiplier);
                getPosition().setY(getPosition().getY()-diagSpeed*multiplier);
            }
            case RIGHT_UP -> {
                getPosition().setX(getPosition().getX()+diagSpeed*multiplier);
                getPosition().setY(getPosition().getY()-diagSpeed*multiplier);
            }
            case DOWN -> getPosition().setY(getPosition().getY()+getSpeed()*multiplier);
            case LEFT_DOWN -> {
                getPosition().setX(getPosition().getX()-diagSpeed*multiplier);
                getPosition().setY(getPosition().getY()+diagSpeed*multiplier);
            }
            case RIGHT_DOWN -> {
                getPosition().setX(getPosition().getX()+diagSpeed*multiplier);
                getPosition().setY(getPosition().getY()+diagSpeed*multiplier);
            }
            case LEFT -> getPosition().setX(getPosition().getX()-getSpeed()*multiplier);
            case RIGHT -> getPosition().setX(getPosition().getX()+getSpeed()*multiplier);
        }
    }

    void collidedWithNonLiving(Direction pushedDirection){

        int multiplier = 5; //5
        int moveX = 0;
        int moveY = 0;
        switch (pushedDirection){
            case RIGHT, RIGHT_UP, RIGHT_DOWN -> moveX = multiplier;
            case LEFT, LEFT_UP, LEFT_DOWN -> moveX = multiplier * -1;
        }
        switch (pushedDirection){
            case UP, LEFT_UP, RIGHT_UP -> moveY = multiplier * -1; //-1
            case DOWN, LEFT_DOWN, RIGHT_DOWN -> moveY = multiplier; //1
        }
        getPosition().setX(getPosition().getX() + moveX);
        getPosition().setY(getPosition().getY() + moveY);
    }
}