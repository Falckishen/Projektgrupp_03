package Model.Weapons;

import Model.Entity;
import Utilities.Direction;
import Utilities.JustAPlaceToKeepThePublicList;

import java.util.HashMap;

class Projectile extends Entity {
    private static HashMap<int, Entity> PROJECTILES_IN_PLAY = JustAPlaceToKeepThePublicList.ACTIVE_PROJECTILES;

    private int lifeLeft;

    protected Projectile(Direction direction, int velocity, int lifeLeft){
        super(0,0,10,10);
        setVelocity(velocity);
        setDirection(direction);
        this.lifeLeft = lifeLeft;

        PROJECTILES_IN_PLAY.put(this.getId(), this);
    }

    //handles 360 degrees
    /*protected Projectile(double direction, int velocity, int lifeLeft){
        super(0,0,10,10);
        setVelocity(velocity);
        setDirection(direction);
        this.lifeLeft = lifeLeft;

        PROJECTILES_IN_PLAY.put(this.getId(), this);
    }*/


    @Override
    public void doOnTick() {
        if(lifeLeft>0){
            move();
            lifeLeft -= 1;
        } else {
            PROJECTILES_IN_PLAY.remove(this.getId());
        }
    }

    private void move(){
        //doesnt handle collision
        if (getDirection() == Direction.LEFT){
            getCurrentPosition().setX( getCurrentPosition().getX() - getVelocity() );
        } else if (getDirection() == Direction.RIGHT){
            getCurrentPosition().setX( getCurrentPosition().getX() + getVelocity() );
        } else if (getDirection() == Direction.UP){
            getCurrentPosition().setY( getCurrentPosition().getY() + getVelocity() );
        } else if (getDirection() == Direction.DOWN){
            getCurrentPosition().setY( getCurrentPosition().getY() - getVelocity() );
        }
    }
}
