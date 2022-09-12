package Controller;

import Utilities.Direction;
import Model.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyboardInput extends KeyAdapter {
    private boolean upKeyPressed = false;
    private boolean leftKeyPressed = false;
    private boolean downKeyPressed = false;
    private boolean rightKeyPressed = false;
    private Game game;
    private ArrayList<Direction> playerDirections;
    public KeyboardInput(Game game) {
        this.game = game;
        playerDirections = new ArrayList<Direction>();
        game.setCurrentPlayerDirections(playerDirections);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W && !upKeyPressed) {
            playerDirections.add(Direction.UP);
            upKeyPressed = true;
            //System.out.println(key + "true");
        }
        if (key == KeyEvent.VK_A && !leftKeyPressed) {
            playerDirections.add(Direction.LEFT);
            leftKeyPressed = true;
            //System.out.println(key + "true");
        }
        if (key == KeyEvent.VK_S && !downKeyPressed) {
            playerDirections.add(Direction.DOWN);
            downKeyPressed = true;
            //System.out.println(key + "true");
        }
        if (key == KeyEvent.VK_D && !rightKeyPressed) {
            playerDirections.add(Direction.RIGHT);
            rightKeyPressed = true;
            //System.out.println(key + "true");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            playerDirections.remove(Direction.UP);
            upKeyPressed = false;
            //System.out.println(key + "false");
        }
        if (key == KeyEvent.VK_A) {
            playerDirections.remove(Direction.LEFT);
            leftKeyPressed = false;
            //System.out.println(key + "false");
        }
        if (key == KeyEvent.VK_S) {
            playerDirections.remove(Direction.DOWN);
            downKeyPressed = false;
            //System.out.println(key + "false");
        }
        if (key == KeyEvent.VK_D) {
            playerDirections.remove(Direction.RIGHT);
            rightKeyPressed = false;
            //System.out.println(key + "false");
        }
    }
}