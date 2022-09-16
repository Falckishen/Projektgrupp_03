package Model.Entities;

import Utilities.Direction;

abstract class Projectile extends Entity {
    private int lifeLeft;

    protected Projectile(int x, int y, int hitBoxRadiusX, int hitBoxRadiusY, int velocity, Direction direction, int lifeLeft) {
        super(x, y, hitBoxRadiusX, hitBoxRadiusY, velocity);
        setDirection(direction);
        this.lifeLeft = lifeLeft;
    }

    @Override
    public void doOnTick() {
        if(lifeLeft>0){
            move();
            lifeLeft -= 1;
        } else {
        }
    }

    private void move(){
        //doesnt handle collision
        if (getDirection() == Direction.LEFT){
            getCurrentPosition().setX( getCurrentPosition().getX() - getSpeed() );
        } else if (getDirection() == Direction.RIGHT){
            getCurrentPosition().setX( getCurrentPosition().getX() + getSpeed() );
        } else if (getDirection() == Direction.UP){
            getCurrentPosition().setY( getCurrentPosition().getY() + getSpeed() );
        } else if (getDirection() == Direction.DOWN){
            getCurrentPosition().setY( getCurrentPosition().getY() - getSpeed() );
        }
    }
}
