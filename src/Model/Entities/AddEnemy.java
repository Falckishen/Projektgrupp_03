package Model.Entities;

import Model.Entities.Monster;

public interface AddEnemy {
    void createMonster();
    Monster createMonster(int coordX, int coordY, int hitboxWidthRadius, int hitboxHeightRadius, int speed, int attackPower);
}
