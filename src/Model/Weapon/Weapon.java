package Model.Weapon;

import Utilities.Direction;

import java.sql.Timestamp;
import java.util.Date;

abstract class Weapon {
    private static final int UPDATE_INTERVAL = 5; // Millisecond


    private final String WeaponType;
    private final int coolDown;

    private final int projectileVelocity;
    private final int projectileLife;

    private Long lastShotFired;
    private Direction direction = Direction.LEFT;


    protected Weapon(String WeaponType, int coolDown, int projectileVelocity, int projectileLife){
        this.WeaponType = WeaponType;
        this.coolDown = coolDown;

        this.projectileVelocity = projectileVelocity;
        this.projectileLife = projectileLife;

        this.lastShotFired = new Date().getTime();
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

    protected void addProjectile(Direction direction, int velocity, int life){
        new Projectile(direction, velocity, life);
    }
    //handles 360 degrees
    /* protected void addProjectile(double direction, int velocity, int life){
        new Projectile(direction, velocity, life);
    }*/

    protected abstract void shoot();

    public void actionShoot(){
        if (new Date().getTime() - lastShotFired > coolDown){
            shoot();
        }
    }
}
