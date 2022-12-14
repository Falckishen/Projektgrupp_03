package Model.Entities.Weapons;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import Model.Entities.AddProjectile;
import Model.Entities.Direction;
import Model.Entities.DirectionHandler;
import Model.Position;

/**
 * @author Ida Altenstedt
 */
public abstract class Weapon {

    private final AddProjectile projectileCreator;
    private final int coolDownSec; //saved as seconds not milliseconds
    private final int projectileVelocity;
    private final int projectileLife;
    private final int projectileAttackPower;
    private final Position playerPosition;
    private Long lastShotFired; //is saved as milliseconds
    public boolean isShooting = true;
    private Direction weaponDirection;
    private final List<Integer> weaponKeyInputs;

    Weapon(AddProjectile projectileCreator, Position playerPosition, int coolDownSec, int projectileVelocity,
                     int projectileLife, int projectileAttackPower, List<Integer> weaponKeyInputs){
        this.projectileCreator = projectileCreator;
        this.coolDownSec = coolDownSec *100; //saved in seconds not milliseconds
        this.projectileVelocity = projectileVelocity;
        this.projectileLife = projectileLife;
        this.projectileAttackPower = projectileAttackPower;
        this.lastShotFired = System.currentTimeMillis();
        this.weaponDirection = Direction.LEFT;
        this.playerPosition = playerPosition;
        this.weaponKeyInputs = weaponKeyInputs;
    }

    protected Direction getWeaponDirection() {
        return weaponDirection;
    }

    private void changeWeaponDirection() {
        //set direction up left if playerKeyInputs.contains(W) && contains(A)
        // should this be moved to controller?
        List<Direction> currentDirections = new ArrayList<>();
        for (Integer input : weaponKeyInputs) {
            //  if ()
            switch (input) {
                case KeyEvent.VK_UP -> currentDirections.add(Direction.UP);
                case KeyEvent.VK_LEFT -> currentDirections.add(Direction.LEFT);
                case KeyEvent.VK_DOWN -> currentDirections.add(Direction.DOWN);
                case KeyEvent.VK_RIGHT -> currentDirections.add(Direction.RIGHT);
            }
        }
        this.weaponDirection = DirectionHandler.findDirection(currentDirections);
        isShooting = this.weaponDirection != Direction.NONE;
    }

    public void actionShoot(){
        changeWeaponDirection();
        if (System.currentTimeMillis() - lastShotFired > coolDownSec && isShooting){ // check that this correlates correctly
            shoot();
            this.lastShotFired = System.currentTimeMillis();
        }
    }

    abstract void shoot();

    protected void addProjectile(Direction projectileDirection) {
        projectileCreator.createSimpleProjectile(playerPosition, projectileDirection, projectileVelocity,
                projectileLife, projectileAttackPower);
    }
}