package Model.Weapon;

import Utilities.Direction;

class SingleShooter extends Weapon{

    protected SingleShooter(String type, int coolDown, int projectileVelocity, int projectileLife) {
        super(type, coolDown, projectileVelocity, projectileLife);
    }

    @Override
    protected void shoot(){
        addProjectile();
    }

    private void addProjectile() {
        super.addProjectile(getDirection(), getProjectileVelocity(), getProjectileLife());
    }
}
