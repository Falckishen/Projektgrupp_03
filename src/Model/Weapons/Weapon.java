package Model.Weapons;

import Utilities.Direction;

abstract class Weapon {
    private static final int UPDATE_INTERVAL = 5; // Millisecond


    private final String WeaponType;
    private final int coolDownSec; //saved as seconds not milliseconds

    private final int projectileVelocity;
    private final int projectileLife;

    private Long lastShotFired; //is saved as milliseconds
    private Direction direction = Direction.LEFT;


    protected Weapon(String WeaponType, int coolDownSec, int projectileVelocity, int projectileLife){
        this.WeaponType = WeaponType;
        this.coolDownSec = coolDownSec *1000; //saved in seconds not milliseconds

        this.projectileVelocity = projectileVelocity;
        this.projectileLife = projectileLife;

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

    protected void addProjectile(Direction direction, int velocity, int life){
        new Projectile(direction, velocity, life);
    }
    //handles 360 degrees
    /* protected void addProjectile(double direction, int velocity, int life){
        new Projectile(direction, velocity, life);
    }*/

    protected abstract void shoot();

    public void actionShoot(){
        if (System.currentTimeMillis() - lastShotFired > coolDownSec){ // check that this correlates correctly
            shoot();
        }
    }
}
