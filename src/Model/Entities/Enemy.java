package Model.Entities;

abstract class Enemy extends Entity {
    private int attackPower;

    protected Enemy(int x, int y, int hitBoxRadiusX, int hitBoxRadiusY, int speed, int attackPower) {
        super(x, y, hitBoxRadiusX, hitBoxRadiusY, speed);
        this.attackPower = attackPower;
    }

    protected int getAttackPower() {
        return attackPower;
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
