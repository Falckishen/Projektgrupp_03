package Model.Weapons;

import Model.Entities.AddProjectile;
import Utilities.Position;

/**
 * @author Ida Altenstedt
 */
import java.util.List;

class SingleShooter extends Weapon{

    protected SingleShooter(AddProjectile projectileCreator, Position playerPosition, int coolDownSec,
                            int projectileVelocity, int projectileLife, int projectileAttackPower, List<Integer> keyInputs) {
        super(projectileCreator, playerPosition, coolDownSec, projectileVelocity, projectileLife,
                projectileAttackPower, keyInputs);
    }

    @Override
    protected void shoot(){
        addProjectile();
    }

    private void addProjectile() {
        getProjectileCreator().createSimpleProjectile(getPlayerPosition(), getWeaponDirection(), getProjectileVelocity(),
                getProjectileLife(), getProjectileAttackPower());
    }
}