package Utilities;

import Model.Entities.Player;

import java.util.List;

public interface AddFriendly {
    void createFriendly(EntityTypes e);
    Player createPlayer(int coordX, int coordY, int hitBoxWidthRadius, int hitBoxHeightRadius, List keyboardInputs);
}
