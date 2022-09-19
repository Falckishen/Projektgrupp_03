package Model.Entities;

import Utilities.Direction;

public interface AddProjectile {
    void createSimpleProjectile(Direction direction, int velocity, int life, int attackPower);
    void createSimpleProjectile(double direction, int velocity, int life, int attackPower);
}
