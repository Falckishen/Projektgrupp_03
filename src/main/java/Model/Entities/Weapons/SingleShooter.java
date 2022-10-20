package Model.Entities.Weapons;

import Model.Entities.AddProjectile;
import Model.Position;

/**
 * @author Ida Altenstedt
 */
import java.util.List;

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