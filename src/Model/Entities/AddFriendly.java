package Model.Entities;

import java.util.List;

/**
 * An interface for creating entities of that are subtypes of Friendly.
 * @author Ida Altenstedt
 */
interface AddFriendly {
    void createPlayer(int coordX, int coordY, List<Integer> keyboardInputs);
}