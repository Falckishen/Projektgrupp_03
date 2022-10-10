package Controller;

import Model.Game;
import Model.MainMenu;
import View.GameView;

import java.util.List;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Alfred Berglöf and Samuel Falck
 */
public class KeyboardInput {

    private final List<Integer> playerInputArrayList;
    private final List<Integer> weaponInputArrayList;
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
    private final MainMenu mainMenu;
    private final GameView mainView;

    public KeyboardInput(JComponent jComponent, List<Integer> playerInputArrayList,
                         MainMenu mainMenu, GameView mainView, List<Integer> weaponInputArrayList) {

        this.playerInputArrayList = playerInputArrayList;
        this.weaponInputArrayList = weaponInputArrayList;

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

        this.mainMenu = mainMenu;
        this.mainView = mainView;
    }

    private class WalkUpActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!wKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_W);
                wKeyPressed = true;
            }
        }
    }

    private class WalkLeftActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!aKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_A);
                aKeyPressed = true;
            }
        }
    }

    private class WalkDownActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!sKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_S);
                sKeyPressed = true;
            }
        }
    }

    private class WalkRightActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!dKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_D);
                dKeyPressed = true;
            }
        }
    }

    private class WalkUpActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_W));
            wKeyPressed = false;
        }
    }

    private class WalkLeftActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_A));
            aKeyPressed = false;
        }
    }

    private class WalkDownActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_S));
            sKeyPressed = false;
        }
    }

    private class WalkRightActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_D));
            dKeyPressed = false;
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

    private class AttackActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_SPACE));
            spacePressed = false;
        }
    }

    private class AttackUpActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!upKeyPressed) {
                weaponInputArrayList.add(KeyEvent.VK_UP);
                upKeyPressed = true;
            }
        }
    }

    private class AttackLeftActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!leftKeyPressed) {
                weaponInputArrayList.add(KeyEvent.VK_LEFT);
                leftKeyPressed = true;
            }
        }
    }

    private class AttackDownActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!downKeyPressed) {
                weaponInputArrayList.add(KeyEvent.VK_DOWN);
                downKeyPressed = true;
            }
        }
    }

    private class AttackRightActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!rightKeyPressed) {
                weaponInputArrayList.add(KeyEvent.VK_RIGHT);
                rightKeyPressed = true;
            }
        }
    }

    private class AttackUpActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            weaponInputArrayList.remove(Integer.valueOf(KeyEvent.VK_UP));
            upKeyPressed = false;
        }
    }

    private class AttackLeftActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            weaponInputArrayList.remove(Integer.valueOf(KeyEvent.VK_LEFT));
            leftKeyPressed = false;
        }
    }

    private class AttackDownActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            weaponInputArrayList.remove(Integer.valueOf(KeyEvent.VK_DOWN));
            downKeyPressed = false;
        }
    }

    private class AttackRightActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            weaponInputArrayList.remove(Integer.valueOf(KeyEvent.VK_RIGHT));
            rightKeyPressed = false;
        }
    }

    private class PauseActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!escPressed){
                escPressed = true;
                if (!getCurrentGame().isGamePaused()){
                    getCurrentGame().pauseGame();
                    //mainView.startPauseMenu();
                }
                else if (getCurrentGame().isGamePaused()) {
                    //mainView.startGame();
                    getCurrentGame().unPauseGame();
                }
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

    private Game getCurrentGame() {
        return mainMenu.getCurrentGame();
    }
}