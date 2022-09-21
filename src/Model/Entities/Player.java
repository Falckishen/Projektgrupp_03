package Model.Entities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import Model.Weapons.Weapon;
import Utilities.Direction;

// TODO Gör till ej public
public class Player extends Friendly {
    private final List<Integer> playerKeyInputs;
    private Weapon weapon;
    private final int defaultSpeed;

    Player(int x, int y, List<Integer> playerInputs, Weapon weapon) {
        super(x, y, 25, 25, 5);
        this.playerKeyInputs = playerInputs;
        this.weapon = weapon;
        this.defaultSpeed = super.getSpeed();
    }

    void getNewWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    @Override
    public void doOnTick() {
        changeDirection();
        move(); // Change to super() when entity has doOnTick
    }

    private void changeDirection() {
        //set direction up left if playerKeyInputs.contains(W) && contains(A)
        // should this be moved to controller?
        List<Direction> currentDirections = new ArrayList<>();
        for (Integer input : playerKeyInputs) {
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
        if(currentDirections.size() == 2) {
            super.setSpeed(defaultSpeed);
            if(currentDirections.contains(Direction.UP) && currentDirections.contains(Direction.LEFT)) {
                super.setDirection(Direction.LEFT_UP);
            }
            if (currentDirections.contains(Direction.UP) && currentDirections.contains((Direction.RIGHT))) {
                super.setDirection(Direction.RIGHT_UP);
            }
            if(currentDirections.contains(Direction.DOWN) && currentDirections.contains(Direction.LEFT)) {
                super.setDirection(Direction.LEFT_DOWN);
            }
            if (currentDirections.contains(Direction.DOWN) && currentDirections.contains((Direction.RIGHT))) {
                super.setDirection(Direction.RIGHT_DOWN);
            }
        }
        // FALL 4: 3 knappar är nere
        if(currentDirections.size() == 3) {
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
                    case UP -> super.setY(super.getCurrentPosition().getY()-super.getSpeed());
                    case DOWN -> super.setY(super.getCurrentPosition().getY()+super.getSpeed());
                    case LEFT -> super.setX(super.getCurrentPosition().getX()-super.getSpeed());
                    case RIGHT -> super.setX(super.getCurrentPosition().getX()+super.getSpeed());
                }
            }
        }
    }*/

    void shootAttack() {
        if(this.weapon != null) {
            this.weapon.actionShoot();
        }
    }
}