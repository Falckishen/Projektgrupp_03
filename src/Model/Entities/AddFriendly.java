package Model.Entities;

import Model.Entities.Player;

import java.util.List;

public interface AddFriendly {
    Player createPlayer(int coordX, int coordY, List keyboardInputs);
}
