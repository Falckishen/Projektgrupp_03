package Model.Entities;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import Model.Entities.Weapons.Weapon;

/**
 * @author Ida Altenstedt & Wanda Wannelöf
 */
class Player extends Friendly {

    private final List<Integer> playerKeyInputs;
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

    /**
     * Checks what KeyEvents are currently in a list of playerKeyInputs and register Directions in the Player object's
     * list of current Directions. Further, asks keyboardHandler to calculate what the sum of all Directions are and sets
     * the Player object's Direction accordingly.
     */
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
        Direction newDirection = DirectionHandler.findDirection(currentDirections);
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