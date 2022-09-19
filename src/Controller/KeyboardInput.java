package Controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class KeyboardInput {

    private boolean upKeyPressed = false;
    private boolean leftKeyPressed = false;
    private boolean downKeyPressed = false;
    private boolean rightKeyPressed = false;
    private final ArrayList<Integer> playerInput;
    Action walkUpActionPressed;
    Action walkLeftActionPressed;
    Action walkDownActionPressed;
    Action walkRightActionPressed;
    Action walkUpActionReleased;
    Action walkLeftActionReleased;
    Action walkDownActionReleased;
    Action walkRightActionReleased;
    Action spacePressed;
    Action spaceReleased;

    public KeyboardInput(JComponent steve, ArrayList<Integer> playerInput){
        this.playerInput = playerInput;

        walkUpActionPressed = new WalkUpActionPressed();
        walkLeftActionPressed = new WalkLeftActionPressed();
        walkDownActionPressed = new WalkDownActionPressed();
        walkRightActionPressed = new WalkRightActionPressed();
        walkUpActionReleased = new WalkUpActionReleased();
        walkLeftActionReleased = new WalkLeftActionReleased();
        walkDownActionReleased = new WalkDownActionReleased();
        walkRightActionReleased = new WalkRightActionReleased();
        spacePressed = new spacePressed();
        spaceReleased = new spaceReleased();

        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false),"walkUpActionPressed");
        steve.getActionMap().put("walkUpActionPressed", walkUpActionPressed);
        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false),"walkLeftActionPressed");
        steve.getActionMap().put("walkLeftActionPressed", walkLeftActionPressed);
        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false),"walkDownActionPressed");
        steve.getActionMap().put("walkDownActionPressed", walkDownActionPressed);
        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false),"walkRightActionPressed");
        steve.getActionMap().put("walkRightActionPressed", walkRightActionPressed);
        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true),"walkUpActionReleased");
        steve.getActionMap().put("walkUpActionReleased", walkUpActionReleased);
        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true),"walkLeftActionReleased");
        steve.getActionMap().put("walkLeftActionReleased", walkLeftActionReleased);
        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true),"walkDownActionReleased");
        steve.getActionMap().put("walkDownActionReleased", walkDownActionReleased);
        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true),"walkRightActionReleased");
        steve.getActionMap().put("walkRightActionReleased", walkRightActionReleased);

        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false),"spacePressed");
        steve.getActionMap().put("spacePressed", spacePressed);
        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true),"spaceReleased");
        steve.getActionMap().put("spaceReleased", spaceReleased);
    }

    public class WalkUpActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!upKeyPressed) {
                playerInput.add(KeyEvent.VK_W);
                upKeyPressed = true;
                System.out.println("W pressed");
            }
        }
    }
    public class WalkLeftActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!leftKeyPressed) {
                playerInput.add(KeyEvent.VK_A);
                leftKeyPressed = true;
                System.out.println("A pressed");
            }
        }
    }
    public class WalkDownActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!downKeyPressed) {
                playerInput.add(KeyEvent.VK_S);
                downKeyPressed = true;
                System.out.println("S pressed");
            }
        }
    }
    public class WalkRightActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!rightKeyPressed) {
                playerInput.add(KeyEvent.VK_D);
                rightKeyPressed = true;
                System.out.println("D pressed");
            }
        }
    }
    public class WalkUpActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInput.remove(KeyEvent.VK_W);
            upKeyPressed = false;
            System.out.println("W released");
        }
    }
    public class WalkLeftActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInput.remove(KeyEvent.VK_A);
            leftKeyPressed = false;
            System.out.println("A released");
        }
    }
    public class WalkDownActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInput.remove(KeyEvent.VK_S);
            downKeyPressed = false;
            System.out.println("S released");
        }
    }
    public class WalkRightActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInput.remove(KeyEvent.VK_D);
            rightKeyPressed = false;
            System.out.println("D released");
        }
    }
    public class spacePressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInput.add(KeyEvent.VK_SPACE)
            System.out.println("bam");
        }
    }

    public class spaceReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInput.remove(KeyEvent.VK_SPACE)
            System.out.println("inte längre bam");
        }
    }
}
