package Model.Entities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import Model.Weapons.Weapon;
import Utilities.Direction;
import Utilities.EntityType;

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
        // FALL 1: Inga/alla knappar är nere
        if(currentDirections.isEmpty() || currentDirections.size() == 4) {
            super.setSpeed(0);
        }
        // FALL 2: 1 Knapp är nere
        if(currentDirections.size() == 1) {
            super.setSpeed(defaultSpeed);
            super.setDirection(currentDirections.get(0));
        }
        // FALL 3: 2 Knappar är nere
        else if(currentDirections.size() == 2) {
            super.setSpeed(defaultSpeed);
            if(currentDirections.contains(Direction.UP) && currentDirections.contains(Direction.LEFT)) {
                super.setDirection(Direction.LEFT_UP);
            }
            else if (currentDirections.contains(Direction.UP) && currentDirections.contains((Direction.RIGHT))) {
                super.setDirection(Direction.RIGHT_UP);
            }
            else if(currentDirections.contains(Direction.DOWN) && currentDirections.contains(Direction.LEFT)) {
                super.setDirection(Direction.LEFT_DOWN);
            }
            else if (currentDirections.contains(Direction.DOWN) && currentDirections.contains((Direction.RIGHT))) {
                super.setDirection(Direction.RIGHT_DOWN);
            }
            else super.setSpeed(0);

        }
        // FALL 4: 3 knappar är nere
        else if(currentDirections.size() == 3) {
            super.setSpeed(defaultSpeed);
            if (currentDirections.contains(Direction.UP) && currentDirections.contains(Direction.DOWN)) {
                if(currentDirections.contains(Direction.LEFT)) super.setDirection(Direction.LEFT);
                else super.setDirection(Direction.RIGHT);
            }
            if (currentDirections.contains(Direction.LEFT) && currentDirections.contains(Direction.RIGHT)) {
                if(currentDirections.contains(Direction.UP)) super.setDirection(Direction.UP);
                else super.setDirection(Direction.DOWN);
            }
        }
    }
/*    private void move() {
        if(!currentPlayerWalkingDirection.isEmpty()) {
            for (Direction direction : currentPlayerWalkingDirection) {
                switch (direction) {
                    case UP -> super.setY(super.getPosition().getY()-super.getSpeed());
                    case DOWN -> super.setY(super.getPosition().getY()+super.getSpeed());
                    case LEFT -> super.setX(super.getPosition().getX()-super.getSpeed());
                    case RIGHT -> super.setX(super.getPosition().getX()+super.getSpeed());
                }
            }
        }
    }*/

    private void shootAttack() {
        if(this.weapon != null && playerKeyInputs.contains(KeyEvent.VK_SPACE)) {
            this.weapon.actionShoot(this.getDirection());
        }
    }
}