package Model.Weapons;

import Model.Entities.AddProjectile;

class SingleShooter extends Weapon{

    protected SingleShooter(AddProjectile projectileCreator, String type, int coolDownSec, int projectileVelocity, int projectileLife) {
        super(projectileCreator, type, coolDownSec, projectileVelocity, projectileLife);
    }

    @Override
    protected void shoot(){
        addProjectile();
    }

    private void addProjectile() {
        getProjectileCreator().createSimpleProjectile(getDirection(), getProjectileVelocity(), getProjectileLife());
    }
}
