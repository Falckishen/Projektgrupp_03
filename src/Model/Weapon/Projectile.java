package Model.Weapon;

import Model.Entity;
import Utilities.Direction;

public class Projectile extends Entity {
    private int lifeLeft;


    Projectile(Direction direction, int velocity, int lifeLeft){
        super(0,0,10,10);
        setVelocity(velocity);
        setDirection(direction);
    }

    @Override
    public void doOnTick() {

    }
}
