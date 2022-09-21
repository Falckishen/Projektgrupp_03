package Model.Entities;

import Utilities.EntityType;

abstract class Enemy extends Entity {
    private final int attackPower;
    private final int attackRange;
    private Position playerPosition;

    protected Enemy(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y, int speed, int attackPower, int attackRange, Position playerPosition) {
        super(entityType, hitBoxRadiusX, hitBoxRadiusY, x, y, speed);
        this.attackPower = attackPower;
        this.attackRange = attackRange;
        this.playerPosition = playerPosition;
    }

    protected int getAttackPower() {
        return attackPower;
    }
    protected int getAttackRange() {
        return attackRange;
    }

    protected void collidedWithPlayer(Position playerPosition){
        //TODO knocked back from player
    }

    protected void collidedWIthEnemy(Position enemyPosition){
        //TODO position needed together with self's direction to know which enemy walked into which
    }

    protected void collidedWithProjectile(int attackPower){
        //TODO looses health in relation to the attackPower
    }
}
