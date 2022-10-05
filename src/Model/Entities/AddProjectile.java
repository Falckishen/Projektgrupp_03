package Model.Entities;

import Utilities.Position;

public interface AddProjectile {
    void createSimpleProjectile(Position position, Direction direction, int velocity, int life, int attackPower);
}