package Model.Entities;

import Model.Entities.Entity;
import Utilities.Direction;
import Utilities.JustAPlaceToKeepThePublicList;

import java.util.HashMap;

class SimpleProjectile extends Projectile {

    protected SimpleProjectile(Direction direction, int velocity, int lifeLeft){
        super(0,0,10,10, velocity, direction, lifeLeft);
    }

    //handles 360 degrees
    /*
    protected Projectile(double direction, int velocity, int lifeLeft){
        super(0,0,10,10);
        setVelocity(velocity);
        setDirection(direction);
        this.lifeLeft = lifeLeft;

        addToHashMap();
    }
    */



}
