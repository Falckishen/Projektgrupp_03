package Model.Weapons;

import Model.Entities.AddProjectile;

class SingleShooter extends Weapon{

    protected SingleShooter(AddProjectile projectileCreator, int coolDownSec, int projectileVelocity, int projectileLife, int projectileAttackPower) {
        super(projectileCreator, coolDownSec, projectileVelocity, projectileLife, projectileAttackPower);
    }

    @Override
    protected void shoot(){
        addProjectile();
    }

    private void addProjectile() {
        getProjectileCreator().createSimpleProjectile(getDirection(), getProjectileVelocity(),
                getProjectileLife(), getProjectileAttackPower());
    }
}