package Controller;

import java.util.List;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Alfred Berglöf and Samuel Falck
 */
public class KeyboardInput {

    private final List<Integer> playerInputArrayList;
    private boolean upKeyPressed = false;
    private boolean leftKeyPressed = false;
    private boolean downKeyPressed = false;
    private boolean rightKeyPressed = false;
    private boolean spacePressed = false;
    private boolean escPressed = false;

    public KeyboardInput(JComponent jComponent, List<Integer> playerInputArrayList) {
        this.playerInputArrayList = playerInputArrayList;

        Action walkUpActionPressed = new WalkUpActionPressed();
        Action walkLeftActionPressed = new WalkLeftActionPressed();
        Action walkDownActionPressed = new WalkDownActionPressed();
        Action walkRightActionPressed = new WalkRightActionPressed();
        Action walkUpActionReleased = new WalkUpActionReleased();
        Action walkLeftActionReleased = new WalkLeftActionReleased();
        Action walkDownActionReleased = new WalkDownActionReleased();
        Action walkRightActionReleased = new WalkRightActionReleased();

        Action attackActionPressed = new AttackActionPressed();
        Action attackActionReleased = new AttackActionReleased();

        Action pauseActionPressed = new PauseActionPressed();
        Action pauseActionReleased = new PauseActionReleased();

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

        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false),"attackActionPressed");
        jComponent.getActionMap().put("attackActionPressed", attackActionPressed);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true),"attackActionReleased");
        jComponent.getActionMap().put("attackActionReleased", attackActionReleased);

        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false),"escPressed");
        jComponent.getActionMap().put("escPressed", pauseActionPressed);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true), "escReleased");
        jComponent.getActionMap().put("escReleased", pauseActionReleased);
    }

    private class WalkUpActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!upKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_W);
                upKeyPressed = true;
            }
        }
    }

    private class WalkLeftActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!leftKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_A);
                leftKeyPressed = true;
            }
        }
    }

    private class WalkDownActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!downKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_S);
                downKeyPressed = true;
            }
        }
    }

    private class WalkRightActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!rightKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_D);
                rightKeyPressed = true;
            }
        }
    }

    private class WalkLeftActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_A));
            leftKeyPressed = false;
        }
    }

    private class WalkDownActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_S));
            downKeyPressed = false;
        }
    }

    private class WalkRightActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_D));
            rightKeyPressed = false;
        }
    }

    private class AttackActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_SPACE));
            spacePressed = false;
        }
    }

    private class AttackActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!spacePressed) {
                playerInputArrayList.add(KeyEvent.VK_SPACE);
                spacePressed = true;
            }
        }
    }

    private class WalkUpActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_W));
            upKeyPressed = false;
        }
    }

    private class PauseActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!escPressed){
                escPressed = true;
                System.out.println("escPressed");
            }
        }
    }

    private class PauseActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e){
            escPressed = false;
            System.out.println("escReleased");
        }
    }
}
