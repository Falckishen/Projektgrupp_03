package Model.Entities;

import Utilities.Direction;

class SimpleProjectile extends Projectile {

    protected SimpleProjectile(Direction direction, int velocity, int lifeLeft, int attackPower){
        super(0,0,10,10, velocity, direction, lifeLeft, attackPower);
    }

    @Override
    protected void CollidedWithEnemy() {
        //whatever happens for a simple projectile when hit
        // setIsDead(true);
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
