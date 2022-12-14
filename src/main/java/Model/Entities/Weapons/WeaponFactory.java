package Model.Entities.Weapons;

import java.util.List;
import Model.Entities.AddProjectile;
import Model.Position;

/**
 * @author Ida Altenstedt
 */
public class WeaponFactory {

    public WeaponFactory(){}

    public Weapon getGun(AddProjectile projectileCreator, Position playerPosition, List<Integer> keyInputs) {
        return new SingleShooter(projectileCreator, playerPosition,3,15,
                40, 1, keyInputs);
    }
}