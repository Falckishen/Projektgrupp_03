package Model.Entities;

import Utilities.EntityType;

abstract class Friendly extends MovableEntity {

    protected Friendly(EntityType entityType, int x, int y, int hitBoxRadiusX, int hitBoxRadiusY, int speed, int health) {
        super(entityType, x, y, hitBoxRadiusX, hitBoxRadiusY, speed, health);
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