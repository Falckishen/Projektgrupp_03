package Controller;

import Model.Direction;
import Model.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class KeyboardInput extends KeyAdapter {
    private boolean UpKeyPressed = false;
    private boolean LeftKeyPressed = false;
    private boolean DownKeyPressed = false;
    private boolean RightKeyPressed = false;
    private Game game;
    private List<Direction> directions;
    public KeyboardInput(Game game) {
        this.game = game;
        directions.clear();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            directions.add(Direction.UP);
            System.out.println(key + "true");
        }
        if (key == KeyEvent.VK_A) {
            directions.add(Direction.LEFT);
            System.out.println(key + "true");
        }
        if (key == KeyEvent.VK_S) {
            directions.add(Direction.DOWN);
            System.out.println(key + "true");
        }
        if (key == KeyEvent.VK_D) {
            directions.add(Direction.RIGHT);
            System.out.println(key + "true");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            directions.remove(Direction.UP);
            System.out.println(key + "false");
        }
        if (key == KeyEvent.VK_A) {
            directions.remove(Direction.LEFT);
            System.out.println(key + "false");
        }
        if (key == KeyEvent.VK_S) {
            directions.remove(Direction.DOWN);
            System.out.println(key + "false");
        }
        if (key == KeyEvent.VK_D) {
            directions.remove(Direction.RIGHT);
            System.out.println(key + "false");
        }
    }
}