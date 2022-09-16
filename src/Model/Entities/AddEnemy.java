package Utilities;

import Model.Entities.Monster;

public interface AddEnemy {
    void createEnemy(EntityTypes e);
    void createMonster();
    Monster createMonster(int coordX, int coordY, int hitboxWidthRadius, int hitboxHeightRadius);
}
