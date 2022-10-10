package Model.Entities;

import java.util.ArrayList;
import java.util.Iterator;
import Utilities.EntityType;
import Utilities.Position;

/**
 * @author Ida Altenstedt
 */
class Monster extends Enemy {

    Monster(int x, int y, Iterable<Friendly> friendliesIterator, int speed, int health, int attackPower){
        super(EntityType.monster, 50, 50, x, y, speed, health, attackPower,
                friendliesIterator);
    }

    @Override
    public void doOnTick() {
        walkTowardsFriendly();
    }
}