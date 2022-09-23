package Controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class KeyboardInput {

    private boolean upKeyPressed = false;
    private boolean leftKeyPressed = false;
    private boolean downKeyPressed = false;
    private boolean rightKeyPressed = false;
    private boolean spacePressed = false;
    private final List<Integer> playerInputArrayList;
    private Action walkUpActionPressed;
    private Action walkLeftActionPressed;
    private Action walkDownActionPressed;
    private Action walkRightActionPressed;
    private Action walkUpActionReleased;
    private Action walkLeftActionReleased;
    private Action walkDownActionReleased;
    private Action walkRightActionReleased;
    private Action attackActionPressed;
    private Action attackActionReleased;

    public KeyboardInput(JComponent steve, ArrayList<Integer> playerInputArrayList){
        this.playerInputArrayList = playerInputArrayList;

        walkUpActionPressed = new WalkUpActionPressed();
        walkLeftActionPressed = new WalkLeftActionPressed();
        walkDownActionPressed = new WalkDownActionPressed();
        walkRightActionPressed = new WalkRightActionPressed();
        walkUpActionReleased = new WalkUpActionReleased();
        walkLeftActionReleased = new WalkLeftActionReleased();
        walkDownActionReleased = new WalkDownActionReleased();
        walkRightActionReleased = new WalkRightActionReleased();
        attackActionPressed = new AttackActionPressed();
        attackActionReleased = new AttackActionReleased();

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
        steve.getActionMap().put("spacePressed", attackActionPressed);
        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true),"spaceReleased");
        steve.getActionMap().put("spaceReleased", attackActionReleased);
    }

    public class WalkUpActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!upKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_W);
                upKeyPressed = true;
            }
        }
    }
    public class WalkLeftActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!leftKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_A);
                leftKeyPressed = true;
            }
        }
    }
    public class WalkDownActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!downKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_S);
                downKeyPressed = true;
            }
        }
    }
    public class WalkRightActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!rightKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_D);
                rightKeyPressed = true;
            }
        }
    }
    public class AttackActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!spacePressed) {
                playerInputArrayList.add(KeyEvent.VK_SPACE);
                spacePressed = true;
            }
        }
    }
    public class WalkUpActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_W));
            upKeyPressed = false;
        }
    }
    public class WalkLeftActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_A));
            leftKeyPressed = false;
        }
    }
    public class WalkDownActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_S));
            downKeyPressed = false;
        }
    }
    public class WalkRightActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_D));
            rightKeyPressed = false;
        }
    }
    public class AttackActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_SPACE));
            spacePressed = false;
        }
    }
}
