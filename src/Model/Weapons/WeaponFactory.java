package Model.Weapons;

import Model.Entities.AddProjectile;
import Utilities.Position;

import java.util.List;

public class WeaponFactory {

    public static Weapon getGun(AddProjectile projectileList, Position playerPosition, List<Integer> keyInputs) {
        return new SingleShooter(projectileList, playerPosition,3,15,
                40, 1, keyInputs);
    }

    public static Weapon getTestWeapon(AddProjectile projectileList, Position playerPosition, List<Integer> keyInputs){
        return new SingleShooter(projectileList, playerPosition,2,50,
                60, 1, keyInputs);
    }
}