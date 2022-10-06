package Model.Weapons;

import Model.Entities.AddProjectile;
import Utilities.Position;

/**
 * @author Ida Altenstedt
 */
class SingleShooter extends Weapon{

    protected SingleShooter(AddProjectile projectileCreator, Position playerPosition, int coolDownSec, int projectileVelocity, int projectileLife, int projectileAttackPower) {
        super(projectileCreator, playerPosition, coolDownSec, projectileVelocity, projectileLife, projectileAttackPower);
    }

    @Override
    protected void shoot(){
        addProjectile();
    }

    private void addProjectile() {
        getProjectileCreator().createSimpleProjectile(getPlayerPosition(), getDirection(), getProjectileVelocity(),
                getProjectileLife(), getProjectileAttackPower());
    }
}