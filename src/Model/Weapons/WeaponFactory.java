package Model.Weapons;

import Model.Entities.AddProjectile;

public class WeaponFactory {

    public static Weapon getGun(AddProjectile projectileList) {
        return new SingleShooter(projectileList,3,7,40, 1);
    }

    public static Weapon getTestWeapon(AddProjectile projectileList){
        return new SingleShooter(projectileList,2,50,60, 1);
    }
}