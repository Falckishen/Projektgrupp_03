package Model.Entities;

import Utilities.EntityType;

abstract class Projectile extends MovableEntity {

    private int lifeLeft;
    private final int attackPower;

    protected Projectile(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y, int velocity,
                         int piercingPower, Direction direction, int lifeLeft, int attackPower) {
        super(entityType, hitBoxRadiusX, hitBoxRadiusY, x, y, velocity,piercingPower);
        setDirection(direction);
        this.lifeLeft = lifeLeft;
        this.attackPower = attackPower;
    }

    protected int getAttackPower() {
        return attackPower;
    }

    protected void CollidedWithEnemy(){
        takeDamage(1);
    }

    protected void collidedWithNonLivingObject(){
        setIsDead(true);
    }

    @Override
    public void doOnTick() {
        if(lifeLeft>0){
            move();
            lifeLeft -= 1;
        } else {
            setIsDead(true);
        }
    }

    //    protected void move(){
//        //doesnt handle collision
//        if (getDirection() == Direction.LEFT){
//            getPosition().setX( getPosition().getX() - getSpeed() );
//        } else if (getDirection() == Direction.RIGHT){
//            getPosition().setX( getPosition().getX() + getSpeed() );
//        } else if (getDirection() == Direction.UP){
//            getPosition().setY( getPosition().getY() + getSpeed() );
//        } else if (getDirection() == Direction.DOWN){
//            getPosition().setY( getPosition().getY() - getSpeed() );
//        }
//    }
}