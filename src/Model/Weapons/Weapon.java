package Model.Weapons;

import Model.Entities.AddProjectile;
import Model.Entities.Direction;
import Utilities.KeyboardHandler;
import Utilities.Position;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Ida Altenstedt
 */



public abstract class Weapon {

    private final AddProjectile projectileCreator;
    private final int coolDownSec; //saved as seconds not milliseconds
    private final int projectileVelocity;
    private final int projectileLife;
    private final int projectileAttackPower;
    private Position playerPosition;
    private Long lastShotFired; //is saved as milliseconds

    private Direction weaponDirection;
    private List<Integer> keyInputs;


    protected Weapon(AddProjectile projectileCreator, Position playerPosition, int coolDownSec, int projectileVelocity,
                     int projectileLife, int projectileAttackPower, List<Integer> keyInputs){
        this.projectileCreator = projectileCreator;
        this.coolDownSec = coolDownSec *100; //saved in seconds not milliseconds
        this.projectileVelocity = projectileVelocity;
        this.projectileLife = projectileLife;
        this.projectileAttackPower = projectileAttackPower;
        this.lastShotFired = System.currentTimeMillis();
        this.weaponDirection = Direction.LEFT;
        this.playerPosition = playerPosition;
        this.keyInputs = keyInputs;
    }

    protected Direction getWeaponDirection() {
        return weaponDirection;
    }

    private void setWeaponDirection(Direction newDirection) {
        weaponDirection = newDirection;
    }

    protected Position getPlayerPosition(){
        return playerPosition;
    }

    protected int getProjectileVelocity() {
        return projectileVelocity;
    }

    protected int getProjectileLife() {
        return projectileLife;
    }

    protected int getProjectileAttackPower(){
        return projectileAttackPower;
    }

    protected AddProjectile getProjectileCreator(){ //just to subclasses has access to the factory
        return projectileCreator;
    }


    /**
     * What happens when the weapon shoots one(1) time.
     */

    private void changeWeaponDirection() {
        //set direction up left if playerKeyInputs.contains(W) && contains(A)
        // should this be moved to controller?
        List<Direction> currentDirections = new ArrayList<>();
        for (Integer input : keyInputs) {
            //  if ()
            switch (input) {
                case KeyEvent.VK_UP -> currentDirections.add(Direction.UP);
                case KeyEvent.VK_LEFT -> currentDirections.add(Direction.LEFT);
                case KeyEvent.VK_DOWN -> currentDirections.add(Direction.DOWN);
                case KeyEvent.VK_RIGHT -> currentDirections.add(Direction.RIGHT);
            }
        }
        Direction newDirection = KeyboardHandler.findDirection(currentDirections);
        setWeaponDirection(newDirection);
    }

    protected abstract void shoot();

    public void actionShoot(){
        changeWeaponDirection();
        if (System.currentTimeMillis() - lastShotFired > coolDownSec){ // check that this correlates correctly
            shoot();
            this.lastShotFired = System.currentTimeMillis();
        }
    }
}