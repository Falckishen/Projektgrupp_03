package Model.Weapons;

import Model.Entities.AddProjectile;
import Utilities.Position;

public class WeaponFactory {

    public static Weapon getGun(AddProjectile projectileList, Position playerPosition) {
        return new SingleShooter(projectileList, playerPosition,3,7,40, 1);
    }

    public static Weapon getTestWeapon(AddProjectile projectileList, Position playerPosition){
        return new SingleShooter(projectileList, playerPosition,2,50,60, 1);
    }
}