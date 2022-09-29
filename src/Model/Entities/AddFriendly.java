package Model.Entities;

import java.util.List;
import Model.Weapons.Weapon;

interface AddFriendly {
    void createPlayer(int coordX, int coordY, List<Integer> keyboardInputs);
}