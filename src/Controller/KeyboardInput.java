package Controller;

import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/**
 * @author Alfred Berglöf and Samuel Falck
 */
public class KeyboardInput {

    private final List<Integer> playerInputArrayList;
    private boolean wKeyPressed = false;
    private boolean aKeyPressed = false;
    private boolean sKeyPressed = false;
    private boolean dKeyPressed = false;
    private boolean upKeyPressed = false;
    private boolean leftKeyPressed = false;
    private boolean downKeyPressed = false;
    private boolean rightKeyPressed = false;
    private boolean spacePressed = false;
    private boolean escPressed = false;

    public KeyboardInput(JComponent jComponent, List<Integer> playerInputArrayList){
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
        Action attackUpActionPressed = new AttackUpActionPressed();
        Action attackUpActionReleased = new AttackUpActionReleased();
        Action attackLeftActionPressed = new AttackLeftActionPressed();
        Action attackLeftActionReleased = new AttackLeftActionReleased();
        Action attackDownActionPressed = new AttackDownActionPressed();
        Action attackDownActionReleased = new AttackDownActionReleased();
        Action attackRightActionPressed = new AttackRightActionPressed();
        Action attackRightActionReleased = new AttackRightActionReleased();
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

        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false),"attackUpActionPressed");
        jComponent.getActionMap().put("attackUpActionPressed", attackUpActionPressed);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false),"attackLeftActionPressed");
        jComponent.getActionMap().put("attackLeftActionPressed", attackLeftActionPressed);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false),"attackDownActionPressed");
        jComponent.getActionMap().put("attackDownActionPressed", attackDownActionPressed);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false),"attackRightActionPressed");
        jComponent.getActionMap().put("attackRightActionPressed", attackRightActionPressed);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true),"attackUpActionReleased");
        jComponent.getActionMap().put("attackUpActionReleased", attackUpActionReleased);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true),"attackLeftActionReleased");
        jComponent.getActionMap().put("attackLeftActionReleased", attackLeftActionReleased);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true),"attackDownActionReleased");
        jComponent.getActionMap().put("attackDownActionReleased", attackDownActionReleased);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true),"attackRightActionReleased");
        jComponent.getActionMap().put("attackRightActionReleased", attackRightActionReleased);

        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false),"escPressed");
        jComponent.getActionMap().put("escPressed", pauseActionPressed);
        jComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true), "escReleased");
        jComponent.getActionMap().put("escReleased", pauseActionReleased);

    }

    class WalkUpActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!wKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_W);
                wKeyPressed = true;
            }
        }
    }
    class WalkLeftActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!aKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_A);
                aKeyPressed = true;
            }
        }
    }
    class WalkDownActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!sKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_S);
                sKeyPressed = true;
            }
        }
    }
    class WalkRightActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!dKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_D);
                dKeyPressed = true;
            }
        }
    }
    class WalkUpActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_W));
            wKeyPressed = false;
        }
    }
    class WalkLeftActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_A));
            aKeyPressed = false;
        }
    }
    class WalkDownActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_S));
            sKeyPressed = false;
        }
    }
    class WalkRightActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_D));
            dKeyPressed = false;
        }
    }
    class AttackActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!spacePressed) {
                playerInputArrayList.add(KeyEvent.VK_SPACE);
                spacePressed = true;
            }
        }
    }
    class AttackActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_SPACE));
            spacePressed = false;
        }
    }
    class AttackUpActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!upKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_UP);
                upKeyPressed = true;
                System.out.println("pressed");
            }
        }
    }
    class AttackLeftActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!leftKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_LEFT);
                leftKeyPressed = true;
                System.out.println("pressed");

            }
        }
    }
    class AttackDownActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!downKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_DOWN);
                downKeyPressed = true;
                System.out.println("pressed");

            }
        }
    }
    class AttackRightActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!rightKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_RIGHT);
                rightKeyPressed = true;
                System.out.println("pressed");

            }
        }
    }
    class AttackUpActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_UP));
            upKeyPressed = false;
            System.out.println("released");

        }
    }
    class AttackLeftActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_LEFT));
            leftKeyPressed = false;
            System.out.println("released");
        }
    }
    class AttackDownActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_DOWN));
            downKeyPressed = false;
            System.out.println("released");
        }
    }
    class AttackRightActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_RIGHT));
            rightKeyPressed = false;
            System.out.println("released");
        }
    }
    public class PauseActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!escPressed){
                escPressed = true;
                System.out.println("escPressed");
            }
        }
    }
    public class PauseActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e){
            escPressed = false;
            System.out.println("escReleased");
        }
    }
}
