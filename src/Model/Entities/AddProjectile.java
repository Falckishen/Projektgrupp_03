package Model.Entities;

import Utilities.Direction;
import Utilities.Position;

public interface AddProjectile {
    void createSimpleProjectile(Position position, Direction direction, int velocity, int life, int attackPower);
    void createSimpleProjectile(Position position, double direction, int velocity, int life, int attackPower);
}