package Model.Entities;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import Model.Entities.Entity;
import Model.Weapons.Weapon;
import Model.Weapons.WeaponFactory;
import Utilities.Direction;

public class Player extends Friendly {
    private final List<Direction> currentPlayerWalkingDirection;
    private Weapon weapon;
    private final AddProjectile projectileCreator;


    Player(int x, int y, List<Direction> currentPlayerWalkingDirection, AddProjectile projectileCreator) {
        super(x, y, 25, 25, 5);
        this.currentPlayerWalkingDirection = currentPlayerWalkingDirection;
        this.weapon = WeaponFactory.getGun(new EntityCreator());
        this.projectileCreator = projectileCreator;
    }

    @Override
    public void doOnTick() {
        move();
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
    public void shootAttack() {
        if(this.weapon != null) {
            this.weapon.actionShoot();
        }
    }
}
