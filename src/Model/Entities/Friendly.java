package Model.Entities;

import Utilities.EntityType;

abstract class Friendly extends MovableEntity {

    protected Friendly(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y,
                       int speed, int health) {
        super(entityType, hitBoxRadiusX, hitBoxRadiusY, x, y, speed, health);
    }

    protected void CollidedWithEnemy(int attackPower){
        // attackPower = how much damage self takes
        takeDamage(attackPower);
        //TODO invulnerability frames

    }

    protected void collidedWithNonLivingObject(Entity object){
        //can't move in this direction
        //gives small knock back so Friendly doesn't get stuck in wall
        move(-2);
    }
}