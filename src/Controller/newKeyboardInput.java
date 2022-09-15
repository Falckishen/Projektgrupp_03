package Controller;

import Model.Game;
import Utilities.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class newKeyboardInput {

    private boolean upKeyPressed = false;
    private boolean leftKeyPressed = false;
    private boolean downKeyPressed = false;
    private boolean rightKeyPressed = false;
    private final Game game;
    private final ArrayList<Direction> playerDirections;
    Action walkUpAction;
    Action walkLeftAction;
    Action walkDownAction;
    Action walkRightAction;
    Action pressSpaceAction;

    public newKeyboardInput(Game game, JComponent steve){
        this.game = game;
        playerDirections = new ArrayList<Direction>();
        game.setCurrentPlayerDirections(playerDirections);

        walkUpAction = new WalkUpAction();
        walkLeftAction = new WalkLeftAction();
        walkDownAction = new WalkDownAction();
        walkRightAction = new WalkRightAction();
        pressSpaceAction = new PressSpaceAction();

        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0),"walkUpAction");
        steve.getActionMap().put("walkUpAction", walkUpAction);
        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0),"walkLeftAction");
        steve.getActionMap().put("walkLeftAction", walkLeftAction);
        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0),"walkDownAction");
        steve.getActionMap().put("walkDownAction", walkDownAction);
        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0),"walkRightAction");
        steve.getActionMap().put("walkRightAction", walkRightAction);
        steve.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0),"pressSpaceAction");
        steve.getActionMap().put("pressSpaceAction", pressSpaceAction);
    }

    public class WalkUpAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Walking Up");
        }
    }
    public class WalkLeftAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Walking Left");
        }
    }
    public class WalkDownAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Walking Down");
        }
    }
    public class WalkRightAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Walking Right");
        }
    }
    public class PressSpaceAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("bam");
        }
    }
}
