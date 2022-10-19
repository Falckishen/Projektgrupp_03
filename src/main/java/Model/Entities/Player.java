package Model.Entities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import Model.Weapons.Weapon;

/**
 * @author Ida Altenstedt
 */
class Player extends Friendly {

    private List<Integer> playerKeyInputs;
    private Weapon weapon;
    private final int defaultSpeed;

    Player(int x, int y, List<Integer> playerInputs) {
        super(EntityType.player, 50, 50, x, y, 5,10);
        defaultSpeed = getSpeed();
        this.playerKeyInputs = playerInputs;
        this.weapon = null;
    }

    void getNewWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    @Override
    public void doOnTick() {
        changeDirection();
        move(); // Change to super() when entity has doOnTick
        shootAttack();
    }

    private void changeDirection() {
        //set direction up left if playerKeyInputs.contains(W) && contains(A)
        // should this be moved to controller?
        List<Direction> currentDirections = new ArrayList<>();
        for (Integer input : playerKeyInputs) {
          //  if ()
            switch (input) {
                case KeyEvent.VK_W -> currentDirections.add(Direction.UP);
                case KeyEvent.VK_A ->  currentDirections.add(Direction.LEFT);
                case KeyEvent.VK_S -> currentDirections.add(Direction.DOWN);
                case KeyEvent.VK_D -> currentDirections.add(Direction.RIGHT);
            }
        }
        Direction newDirection = KeyboardHandler.findDirection(currentDirections);
        super.setDirection(newDirection);
        if (newDirection == Direction.NONE) setSpeed(0);
        else setSpeed(defaultSpeed);
    }

    private void shootAttack() {
        if(this.weapon != null) {
            weapon.actionShoot();
        }
    }
}