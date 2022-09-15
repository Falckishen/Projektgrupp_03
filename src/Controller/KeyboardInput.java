package Controller;

import Model.Game;
import Utilities.Direction;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class KeyboardInput {

    private boolean upKeyPressed = false;
    private boolean leftKeyPressed = false;
    private boolean downKeyPressed = false;
    private boolean rightKeyPressed = false;
    private final Game game;
    private final ArrayList<Direction> playerDirections;
    Action walkUpActionPressed;
    Action walkLeftActionPressed;
    Action walkDownActionPressed;
    Action walkRightActionPressed;
    Action walkUpActionReleased;
    Action walkLeftActionReleased;
    Action walkDownActionReleased;
    Action walkRightActionReleased;
    Action pressSpaceAction;

    public KeyboardInput(Game game, JComponent steve){
        this.game = game;
        playerDirections = new ArrayList<Direction>();
        game.setCurrentPlayerDirections(playerDirections);

        walkUpActionPressed = new WalkUpActionPressed();
        walkLeftActionPressed = new WalkLeftActionPressed();
        walkDownActionPressed = new WalkDownActionPressed();
        walkRightActionPressed = new WalkRightActionPressed();
        walkUpActionReleased = new WalkUpActionReleased();
        walkLeftActionReleased = new WalkLeftActionReleased();
        walkDownActionReleased = new WalkDownActionReleased();
        walkRightActionReleased = new WalkRightActionReleased();
        pressSpaceAction = new PressSpaceAction();

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

        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false),"pressSpaceAction");
        steve.getActionMap().put("pressSpaceAction", pressSpaceAction);
    }

    public class WalkUpActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!upKeyPressed) {
                playerDirections.add(Direction.UP);
                upKeyPressed = true;
                System.out.println("W pressed");
            }
        }
    }
    public class WalkLeftActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!leftKeyPressed) {
                playerDirections.add(Direction.LEFT);
                leftKeyPressed = true;
                System.out.println("A pressed");
            }
        }
    }
    public class WalkDownActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!downKeyPressed) {
                playerDirections.add(Direction.DOWN);
                downKeyPressed = true;
                System.out.println("S pressed");
            }
        }
    }
    public class WalkRightActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!rightKeyPressed) {
                playerDirections.add(Direction.RIGHT);
                rightKeyPressed = true;
                System.out.println("D pressed");
            }
        }
    }
    public class WalkUpActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerDirections.remove(Direction.UP);
            upKeyPressed = false;
            System.out.println("W released");
        }
    }
    public class WalkLeftActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerDirections.remove(Direction.LEFT);
            leftKeyPressed = false;
            System.out.println("A released");
        }
    }
    public class WalkDownActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerDirections.remove(Direction.DOWN);
            downKeyPressed = false;
            System.out.println("S released");
        }
    }
    public class WalkRightActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerDirections.remove(Direction.RIGHT);
            rightKeyPressed = false;
            System.out.println("D released");
        }
    }
    public class PressSpaceAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("bam");
        }
    }
}
