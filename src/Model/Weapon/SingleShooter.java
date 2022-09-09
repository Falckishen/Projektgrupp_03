package Model.Weapon;

import Utilities.Direction;

class SingleShooter extends Weapon{

    protected SingleShooter(String type, int coolDownSec, int projectileVelocity, int projectileLife) {
        super(type, coolDownSec, projectileVelocity, projectileLife);
    }

    @Override
    protected void shoot(){
        addProjectile();
    }

    private void addProjectile() {
        super.addProjectile(getDirection(), getProjectileVelocity(), getProjectileLife());
    }
}
