package Model.Entities;

import java.util.List;
import Model.Weapons.Weapon;

public interface AddFriendly {
    Player createPlayer(int coordX, int coordY, List<Integer> keyboardInputs, Weapon weapon);
}
