package Model;

// Game engine, main class for Model.
// Follows the facade pattern, this is the only public class in Model
public class Game {

    private Entity[] Entities;

    public Game() {

    }

    // Controller calls this method to move the player
    public void movePlayer() {

    }

    public Entity getEntityOnPosistion(Position position) {
        for(Entity entity : Entities) {
        }
    }
}