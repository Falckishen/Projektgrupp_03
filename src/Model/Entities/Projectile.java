package Model.Entities;

import Utilities.Direction;
import Utilities.EntityType;

abstract class Projectile extends Entity {
    private int lifeLeft;
    private int attackPower;

    protected Projectile(EntityType entityType, int x, int y, int hitBoxRadiusX, int hitBoxRadiusY, int velocity, Direction direction, int lifeLeft, int attackPower) {
        super(entityType, x, y, hitBoxRadiusX, hitBoxRadiusY, velocity);
        setDirection(direction);
        this.lifeLeft = lifeLeft;
        this.attackPower = attackPower;
    }

    protected int getAttackPower() {
        return attackPower;
    }

    protected abstract void CollidedWithEnemy();

    @Override
    public void doOnTick() {
        if(lifeLeft>0){
            move();
            lifeLeft -= 1;
        } else {
        }
    }

//    protected void move(){
//        //doesnt handle collision
//        if (getDirection() == Direction.LEFT){
//            getCurrentPosition().setX( getCurrentPosition().getX() - getSpeed() );
//        } else if (getDirection() == Direction.RIGHT){
//            getCurrentPosition().setX( getCurrentPosition().getX() + getSpeed() );
//        } else if (getDirection() == Direction.UP){
//            getCurrentPosition().setY( getCurrentPosition().getY() + getSpeed() );
//        } else if (getDirection() == Direction.DOWN){
//            getCurrentPosition().setY( getCurrentPosition().getY() - getSpeed() );
//        }
//    }


}
