package Controller;

import Utilities.Direction;
import Model.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyboardInput extends KeyAdapter {
    private boolean upKeyPressed = false;
    private boolean leftKeyPressed = false;
    private boolean downKeyPressed = false;
    private boolean rightKeyPressed = false;
    private Game game;
    private ArrayList<Direction> directions;
    public KeyboardInput(Game game) {
        this.game = game;
        directions = new ArrayList<Direction>();
        game.setListOfCurrentPlayerDirection(directions);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W && !upKeyPressed) {
            directions.add(Direction.UP);
            upKeyPressed = true;
            //System.out.println(key + "true");
        }
        if (key == KeyEvent.VK_A && !leftKeyPressed) {
            directions.add(Direction.LEFT);
            leftKeyPressed = true;
            //System.out.println(key + "true");
        }
        if (key == KeyEvent.VK_S && !downKeyPressed) {
            directions.add(Direction.DOWN);
            downKeyPressed = true;
            //System.out.println(key + "true");
        }
        if (key == KeyEvent.VK_D && !rightKeyPressed) {
            directions.add(Direction.RIGHT);
            rightKeyPressed = true;
            //System.out.println(key + "true");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            directions.remove(Direction.UP);
            upKeyPressed = false;
            //System.out.println(key + "false");
        }
        if (key == KeyEvent.VK_A) {
            directions.remove(Direction.LEFT);
            leftKeyPressed = false;
            //System.out.println(key + "false");
        }
        if (key == KeyEvent.VK_S) {
            directions.remove(Direction.DOWN);
            downKeyPressed = false;
            //System.out.println(key + "false");
        }
        if (key == KeyEvent.VK_D) {
            directions.remove(Direction.RIGHT);
            rightKeyPressed = false;
            //System.out.println(key + "false");
        }
    }
}