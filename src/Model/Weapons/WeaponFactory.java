package Model.Weapons;

import Model.Entities.AddProjectile;

public class WeaponFactory {

    public static Weapon getGun(AddProjectile projectileList){
        return new SingleShooter(projectileList,"Gun",32,7,40);
    }

    public static Weapon getTestWeapon(AddProjectile projectileList){
        return new SingleShooter(projectileList,"testWeapon",2,50,60);
    }
}
