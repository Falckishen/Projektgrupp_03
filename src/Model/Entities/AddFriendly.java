package Model.Entities;

import Model.Entities.Player;
import Model.Weapons.Weapon;

import java.util.List;

public interface AddFriendly {
    Player createPlayer(int coordX, int coordY, List<Integer> keyboardInputs, Weapon weapon);
}
