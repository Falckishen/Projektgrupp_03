package Model.Weapons;

import Model.Entities.AddProjectile;
import Utilities.Position;

/**
 * @author Ida Altenstedt
 */
public class WeaponFactory {

    public static Weapon getGun(AddProjectile projectileCreator, Position playerPosition) {
        return new SingleShooter(projectileCreator, playerPosition,3,15,40, 1);
    }

    public static Weapon getTestWeapon(AddProjectile projectileCreator, Position playerPosition){
        return new SingleShooter(projectileCreator, playerPosition,2,50,60, 1);
    }
}