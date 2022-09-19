package Controller;

import Utilities.Direction;
import Model.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class oldKeyboardInput implements KeyListener {
    private boolean upKeyPressed = false;
    private boolean leftKeyPressed = false;
    private boolean downKeyPressed = false;
    private boolean rightKeyPressed = false;
    private final ArrayList<Direction> playerDirections;

    public oldKeyboardInput(Game game) {
        playerDirections = new ArrayList<Direction>();
        game.setCurrentPlayerDirections(playerDirections);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W && !upKeyPressed) {
            playerDirections.add(Direction.UP);
            upKeyPressed = true;
        }
        if (key == KeyEvent.VK_A && !leftKeyPressed) {
            playerDirections.add(Direction.LEFT);
            leftKeyPressed = true;
        }
        if (key == KeyEvent.VK_S && !downKeyPressed) {
            playerDirections.add(Direction.DOWN);
            downKeyPressed = true;
        }
        if (key == KeyEvent.VK_D && !rightKeyPressed) {
            playerDirections.add(Direction.RIGHT);
            rightKeyPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            playerDirections.remove(Direction.UP);
            upKeyPressed = false;
        }
        if (key == KeyEvent.VK_A) {
            playerDirections.remove(Direction.LEFT);
            leftKeyPressed = false;
        }
        if (key == KeyEvent.VK_S) {
            playerDirections.remove(Direction.DOWN);
            downKeyPressed = false;
        }
        if (key == KeyEvent.VK_D) {
            playerDirections.remove(Direction.RIGHT);
            rightKeyPressed = false;
        }
    }
}