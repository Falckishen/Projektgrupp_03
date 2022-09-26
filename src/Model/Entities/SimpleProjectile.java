package Model.Entities;

import Utilities.Direction;
import Utilities.EntityType;

class SimpleProjectile extends Projectile {

    protected SimpleProjectile(Direction direction, int velocity, int lifeLeft, int attackPower){
        super(EntityType.simpleProjectile, 0,0,10,10, velocity, 1, direction, lifeLeft, attackPower);
    }
}