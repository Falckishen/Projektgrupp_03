package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {
    private boolean UpKeyPressed = false;
    private boolean LeftKeyPressed = false;
    private boolean DownKeyPressed = false;
    private boolean RightKeyPressed = false;

    public KeyboardInput() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            UpKeyPressed = true;
            System.out.println(key + "true");
        }
        if (key == KeyEvent.VK_A) {
            LeftKeyPressed = true;
            System.out.println(key + "true");
        }
        if (key == KeyEvent.VK_S) {
            DownKeyPressed = true;
            System.out.println(key + "true");
        }
        if (key == KeyEvent.VK_D) {
            RightKeyPressed = true;
            System.out.println(key + "true");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            UpKeyPressed = false;
            System.out.println(key + "false");
        }
        if (key == KeyEvent.VK_A) {
            LeftKeyPressed = false;
            System.out.println(key + "false");
        }
        if (key == KeyEvent.VK_S) {
            DownKeyPressed = false;
            System.out.println(key + "false");
        }
        if (key == KeyEvent.VK_D) {
            RightKeyPressed = false;
            System.out.println(key + "false");
        }
    }
}