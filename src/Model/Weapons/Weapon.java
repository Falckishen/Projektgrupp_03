package Model.Weapons;

import Model.Entities.AddProjectile;
import Utilities.Direction;

public abstract class Weapon {

    private final AddProjectile projectileCreator;
    private final String weaponType;
    private final int coolDownSec; // Saved as seconds not milliseconds
    private final int projectileVelocity;
    private final int projectileLife;
    private final int projectileAttackPower;
    private Long lastShotFired; // Is saved as milliseconds
    private Direction direction = Direction.LEFT;

    protected Weapon(AddProjectile projectileCreator, String weaponType, int coolDownSec, int projectileVelocity, int projectileLife, int projectileAttackPower) {
        this.projectileCreator = projectileCreator;
        this.weaponType = weaponType;
        this.coolDownSec = coolDownSec *1000; //saved in seconds not milliseconds

        this.projectileVelocity = projectileVelocity;
        this.projectileLife = projectileLife;
        this.projectileAttackPower = projectileAttackPower;

        this.lastShotFired = System.currentTimeMillis();
    }

    protected Direction getDirection() {
        return direction;
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

    protected abstract void shoot();

    public void actionShoot(){
        if (System.currentTimeMillis() - lastShotFired > coolDownSec){ // Check that this correlates correctly
            shoot();
        }
    }
}