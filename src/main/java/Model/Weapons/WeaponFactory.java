package Model.Weapons;

import Model.Entities.AddProjectile;
import Model.Position;
import java.util.List;


/**
 * @author Ida Altenstedt
 */
public class WeaponFactory {

    public static Weapon getGun(AddProjectile projectileCreator, Position playerPosition, List<Integer> keyInputs) {
        return new SingleShooter(projectileCreator, playerPosition,3,15,
                40, 1, keyInputs);
    }
}