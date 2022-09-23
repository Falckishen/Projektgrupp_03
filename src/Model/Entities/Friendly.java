package Model.Entities;

import Utilities.EntityType;

abstract class Friendly extends Entity {

    protected Friendly(EntityType entityType, int x, int y, int hitBoxRadiusX, int hitBoxRadiusY, int speed) {
        super(entityType, x, y, hitBoxRadiusX, hitBoxRadiusY, speed);
    }

    protected void CollidedWithEnemy(int attackPower){
        //setIsDead(true);  // out commented since dying ends the game
        //TODO attackPower = how much damage self takes
    }

    protected void collidedWithNonLivingObject(AllObjects object){
        //can't move in this direction
        //gives small knock back so Friendly doesn't get stuck in wall
        move(-2);
    }
}