package Model.Entities;

import Utilities.Direction;
import Utilities.EntityType;
import Utilities.Position;

class SimpleProjectile extends Projectile {

    protected SimpleProjectile(int x, int y, Direction direction, int velocity, int lifeLeft, int attackPower){
        super(EntityType.simpleProjectile, 10, 10, x, y, velocity, 1,
                direction, lifeLeft, attackPower);
    }
}