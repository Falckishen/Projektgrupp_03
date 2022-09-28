package Model.Weapons;

import Model.Entities.AddProjectile;
import Utilities.Direction;

public abstract class Weapon {

    private final AddProjectile projectileCreator;
    private final int coolDownSec; //saved as seconds not milliseconds
    private final int projectileVelocity;
    private final int projectileLife;
    private final int projectileAttackPower;
    private Long lastShotFired; //is saved as milliseconds
    private Direction direction;

    protected Weapon(AddProjectile projectileCreator, int coolDownSec, int projectileVelocity, int projectileLife, int projectileAttackPower){
        this.projectileCreator = projectileCreator;
        this.coolDownSec = coolDownSec *1000; //saved in seconds not milliseconds
        this.projectileVelocity = projectileVelocity;
        this.projectileLife = projectileLife;
        this.projectileAttackPower = projectileAttackPower;
        this.lastShotFired = System.currentTimeMillis();
        this.direction = Direction.LEFT;
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

    public void actionShoot(Direction shootDirection){
        System.out.println("Boom1");
        //projectileCreator.createSimpleProjectile(
        //        shootDirection, getProjectileVelocity(),getProjectileLife(),getProjectileAttackPower());
        this.direction = shootDirection;
        //System.out.println("BOOM");

        //shoot();

      //  projectileCreator.createSimpleProjectile(
       //         shootDirection, getProjectileVelocity(),getProjectileLife(),getProjectileAttackPower());
        System.out.println(System.currentTimeMillis());
        System.out.println(lastShotFired);
        System.out.println(System.currentTimeMillis() - lastShotFired);

        System.out.println(coolDownSec);

        if (System.currentTimeMillis() - lastShotFired > coolDownSec){ // check that this correlates correctly
            shoot();
            this.lastShotFired = System.currentTimeMillis();
            System.out.println("Boom2");
        }
        System.out.println("loading");
    }
}