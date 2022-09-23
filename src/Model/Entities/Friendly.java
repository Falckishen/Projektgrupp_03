package Model.Entities;

import Utilities.EntityType;

abstract class Friendly extends Entity {

    protected Friendly(EntityType entityType, int x, int y, int hitBoxRadiusX, int hitBoxRadiusY, int speed) {
        super(entityType, x, y, hitBoxRadiusX, hitBoxRadiusY, speed);
    }

    protected void CollidedWithEnemy(int attackPower){
        //setIsDead(true);
        //TODO attackPower = how much damage self takes
    }

    protected void collidedWithNonLivingObject(AllObjects object){
        //TODO can't move in this direction
    }
}