package Controller;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class KeyboardInput {

    private boolean upKeyPressed = false;
    private boolean leftKeyPressed = false;
    private boolean downKeyPressed = false;
    private boolean rightKeyPressed = false;
    private final ArrayList<Integer> playerInputArrayList;

    public KeyboardInput(JComponent jComponent, ArrayList<Integer> playerInputArrayList){
        this.playerInputArrayList = playerInputArrayList;

        Action walkUpActionPressed = new WalkUpActionPressed();
        Action walkLeftActionPressed = new WalkLeftActionPressed();
        Action walkDownActionPressed = new WalkDownActionPressed();
        Action walkRightActionPressed = new WalkRightActionPressed();
        Action walkUpActionReleased = new WalkUpActionReleased();
        Action walkLeftActionReleased = new WalkLeftActionReleased();
        Action walkDownActionReleased = new WalkDownActionReleased();
        Action walkRightActionReleased = new WalkRightActionReleased();
        Action spacePressed = new spacePressed();
        Action spaceReleased = new spaceReleased();

        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false),"walkUpActionPressed");
        jComponent.getActionMap().put("walkUpActionPressed", walkUpActionPressed);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false),"walkLeftActionPressed");
        jComponent.getActionMap().put("walkLeftActionPressed", walkLeftActionPressed);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false),"walkDownActionPressed");
        jComponent.getActionMap().put("walkDownActionPressed", walkDownActionPressed);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false),"walkRightActionPressed");
        jComponent.getActionMap().put("walkRightActionPressed", walkRightActionPressed);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true),"walkUpActionReleased");
        jComponent.getActionMap().put("walkUpActionReleased", walkUpActionReleased);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true),"walkLeftActionReleased");
        jComponent.getActionMap().put("walkLeftActionReleased", walkLeftActionReleased);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true),"walkDownActionReleased");
        jComponent.getActionMap().put("walkDownActionReleased", walkDownActionReleased);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true),"walkRightActionReleased");
        jComponent.getActionMap().put("walkRightActionReleased", walkRightActionReleased);

        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false),"spacePressed");
        jComponent.getActionMap().put("spacePressed", spacePressed);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true),"spaceReleased");
        jComponent.getActionMap().put("spaceReleased", spaceReleased);
    }

    class WalkUpActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!upKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_W);
                upKeyPressed = true;
            }
        }
    }
    class WalkLeftActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!leftKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_A);
                leftKeyPressed = true;
            }
        }
    }
    class WalkDownActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!downKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_S);
                downKeyPressed = true;
            }
        }
    }
    class WalkRightActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!rightKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_D);
                rightKeyPressed = true;
            }
        }
    }
    class spacePressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.add(KeyEvent.VK_SPACE);
        }
    }
    class WalkUpActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_W));
            upKeyPressed = false;
        }
    }
    class WalkLeftActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_A));
            leftKeyPressed = false;
        }
    }
    class WalkDownActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_S));
            downKeyPressed = false;
        }
    }
    class WalkRightActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_D));
            rightKeyPressed = false;
        }
    }
    class spaceReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_SPACE));
        }
    }
}
