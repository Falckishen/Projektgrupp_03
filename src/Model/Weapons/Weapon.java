package Model.Weapons;

import Model.Entities.AddProjectile;
import Model.Entities.Direction;
import Utilities.Position;

public abstract class Weapon {

    private final AddProjectile projectileCreator;
    private final int coolDownSec; //saved as seconds not milliseconds
    private final int projectileVelocity;
    private final int projectileLife;
    private final int projectileAttackPower;
    private Long lastShotFired; //is saved as milliseconds
    private Direction weaponDirection;
    private Position playerPosition;

    protected Weapon(AddProjectile projectileCreator, Position playerPosition, int coolDownSec, int projectileVelocity, int projectileLife, int projectileAttackPower){
        this.projectileCreator = projectileCreator;
        this.coolDownSec = coolDownSec *100; //saved in seconds not milliseconds
        this.projectileVelocity = projectileVelocity;
        this.projectileLife = projectileLife;
        this.projectileAttackPower = projectileAttackPower;
        this.lastShotFired = System.currentTimeMillis();
        this.weaponDirection = Direction.LEFT;
        this.playerPosition = playerPosition;
    }

    protected Direction getWeaponDirection() {
        return weaponDirection;
    }

    protected Position getPlayerPosition(){
        return playerPosition;
    }

    protected int getProjectileVelocity() {
        return projectileVelocity;
    }

    protected int getProjectileLife() {
        return projectileLife;
    }

    protected int getProjectileAttackPower(){
        return projectileAttackPower;
    }

    protected AddProjectile getProjectileCreator(){ //just to subclasses has access to the factory
        return projectileCreator;
    }


    private void changeDirection(){

    }

    protected abstract void shoot();

    public void actionShoot(){

        if (System.currentTimeMillis() - lastShotFired > coolDownSec){ // check that this correlates correctly
            shoot();
            this.lastShotFired = System.currentTimeMillis();
        }
    }
}