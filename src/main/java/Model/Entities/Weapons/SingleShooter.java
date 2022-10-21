package Model.Entities.Weapons;

import java.util.List;
import Model.Entities.AddProjectile;
import Model.Position;

/**
 * @author Ida Altenstedt
 */
class SingleShooter extends Weapon{

    SingleShooter(AddProjectile projectileCreator, Position playerPosition, int coolDownSec,
                            int projectileVelocity, int projectileLife, int projectileAttackPower,
                            List<Integer> keyInputs) {
        super(projectileCreator, playerPosition, coolDownSec, projectileVelocity, projectileLife,
                projectileAttackPower, keyInputs);
    }

    @Override
    void shoot(){
        addProjectile();
    }

    private void addProjectile() {
        addProjectile(getWeaponDirection());
    }
}