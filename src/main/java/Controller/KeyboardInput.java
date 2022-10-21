package Controller;

import Model.Game;
import Model.MainMenu;
import Model.ViewObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @author Alfred Berglöf and Samuel Falck
 */
public class KeyboardInput {

    private final MainMenu mainMenu;
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
    private boolean escPressed = false;

    /**
     * This class initiates keyboard input, it functions by binding inputs from an input map to different actions.
     *
     * @param mainMenu reference to Model.
     * @param mainView reference to main View.
     */
    public KeyboardInput(MainMenu mainMenu, ViewObserver mainView) {

        this.mainMenu = mainMenu;

        JComponent mainFrameRootPane = mainView.getMainFrameRootPane();

        this.playerInputArrayList = mainMenu.getMovementInputList();
        this.weaponInputArrayList = mainMenu.getWeaponInputList();

        Action walkUpActionPressed = new WalkUpActionPressed();
        Action walkLeftActionPressed = new WalkLeftActionPressed();
        Action walkDownActionPressed = new WalkDownActionPressed();
        Action walkRightActionPressed = new WalkRightActionPressed();
        Action walkUpActionReleased = new WalkUpActionReleased();
        Action walkLeftActionReleased = new WalkLeftActionReleased();
        Action walkDownActionReleased = new WalkDownActionReleased();
        Action walkRightActionReleased = new WalkRightActionReleased();

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

        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false),"walkUpActionPressed");
        mainFrameRootPane.getActionMap().put("walkUpActionPressed", walkUpActionPressed);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false),"walkLeftActionPressed");
        mainFrameRootPane.getActionMap().put("walkLeftActionPressed", walkLeftActionPressed);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false),"walkDownActionPressed");
        mainFrameRootPane.getActionMap().put("walkDownActionPressed", walkDownActionPressed);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false),"walkRightActionPressed");
        mainFrameRootPane.getActionMap().put("walkRightActionPressed", walkRightActionPressed);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true),"walkUpActionReleased");
        mainFrameRootPane.getActionMap().put("walkUpActionReleased", walkUpActionReleased);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true),"walkLeftActionReleased");
        mainFrameRootPane.getActionMap().put("walkLeftActionReleased", walkLeftActionReleased);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true),"walkDownActionReleased");
        mainFrameRootPane.getActionMap().put("walkDownActionReleased", walkDownActionReleased);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true),"walkRightActionReleased");
        mainFrameRootPane.getActionMap().put("walkRightActionReleased", walkRightActionReleased);

        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false),"attackUpActionPressed");
        mainFrameRootPane.getActionMap().put("attackUpActionPressed", attackUpActionPressed);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false),"attackLeftActionPressed");
        mainFrameRootPane.getActionMap().put("attackLeftActionPressed", attackLeftActionPressed);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false),"attackDownActionPressed");
        mainFrameRootPane.getActionMap().put("attackDownActionPressed", attackDownActionPressed);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false),"attackRightActionPressed");
        mainFrameRootPane.getActionMap().put("attackRightActionPressed", attackRightActionPressed);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true),"attackUpActionReleased");
        mainFrameRootPane.getActionMap().put("attackUpActionReleased", attackUpActionReleased);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true),"attackLeftActionReleased");
        mainFrameRootPane.getActionMap().put("attackLeftActionReleased", attackLeftActionReleased);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true),"attackDownActionReleased");
        mainFrameRootPane.getActionMap().put("attackDownActionReleased", attackDownActionReleased);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true),"attackRightActionReleased");
        mainFrameRootPane.getActionMap().put("attackRightActionReleased", attackRightActionReleased);

        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false),"escPressed");
        mainFrameRootPane.getActionMap().put("escPressed", pauseActionPressed);
        mainFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true), "escReleased");
        mainFrameRootPane.getActionMap().put("escReleased", pauseActionReleased);
    }

    /**
     * If "w" is pressed adds VK_W to playerInputArrayList
     */
    private class WalkUpActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!wKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_W);
                wKeyPressed = true;
            }
        }
    }
    /**
     * If "a" is pressed adds VK_A to playerInputArrayList
     */
    private class WalkLeftActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!aKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_A);
                aKeyPressed = true;
            }
        }
    }
    /**
     * If "s" is pressed adds VK_S to playerInputArrayList
     */
    private class WalkDownActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!sKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_S);
                sKeyPressed = true;
            }
        }
    }
    /**
     * If "d" is pressed adds VK_D to playerInputArrayList
     */
    private class WalkRightActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!dKeyPressed) {
                playerInputArrayList.add(KeyEvent.VK_D);
                dKeyPressed = true;
            }
        }
    }
    /**
     * When "w" is released removes VK_W from playerInputArrayList
     */
    private class WalkUpActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_W));
            wKeyPressed = false;
        }
    }
    /**
     * When "a" is released removes VK_A from playerInputArrayList
     */
    private class WalkLeftActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_A));
            aKeyPressed = false;
        }
    }
    /**
     * When "s" is released removes VK_S from playerInputArrayList
     */
    private class WalkDownActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_S));
            sKeyPressed = false;
        }
    }
    /**
     * When "d" is released removes VK_D from playerInputArrayList
     */
    private class WalkRightActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            playerInputArrayList.remove(Integer.valueOf(KeyEvent.VK_D));
            dKeyPressed = false;
        }
    }
    /**
     * If "up-key" is pressed adds VK_UP to playerInputArrayList
     */
    private class AttackUpActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!upKeyPressed) {
                weaponInputArrayList.add(KeyEvent.VK_UP);
                upKeyPressed = true;
            }
        }
    }
    /**
     * If "left-key" is pressed adds VK_LEFT to playerInputArrayList
     */
    private class AttackLeftActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!leftKeyPressed) {
                weaponInputArrayList.add(KeyEvent.VK_LEFT);
                leftKeyPressed = true;
            }
        }
    }
    /**
     * If "down-key" is pressed adds VK_DOWN to playerInputArrayList
     */
    private class AttackDownActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!downKeyPressed) {
                weaponInputArrayList.add(KeyEvent.VK_DOWN);
                downKeyPressed = true;
            }
        }
    }
    /**
     * If "right-key" is pressed adds VK_RIGHT to playerInputArrayList
     */
    private class AttackRightActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!rightKeyPressed) {
                weaponInputArrayList.add(KeyEvent.VK_RIGHT);
                rightKeyPressed = true;
            }
        }
    }
    /**
     * When "up-key" is released removes VK_UP from playerInputArrayList
     */
    private class AttackUpActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            weaponInputArrayList.remove(Integer.valueOf(KeyEvent.VK_UP));
            upKeyPressed = false;
        }
    }
    /**
     * When "left-key" is released removes VK_LEFT from playerInputArrayList
     */
    private class AttackLeftActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            weaponInputArrayList.remove(Integer.valueOf(KeyEvent.VK_LEFT));
            leftKeyPressed = false;
        }
    }
    /**
     * When "down-key" is released removes VK_DOWN from playerInputArrayList
     */
    private class AttackDownActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            weaponInputArrayList.remove(Integer.valueOf(KeyEvent.VK_DOWN));
            downKeyPressed = false;
        }
    }
    /**
     * When "right-key" is released removes VK_RIGHT from playerInputArrayList
     */
    private class AttackRightActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            weaponInputArrayList.remove(Integer.valueOf(KeyEvent.VK_RIGHT));
            rightKeyPressed = false;
        }
    }
    /**
     * If "esc" is pressed, calls the pauseGame() or unPauseGame() method in the mainMenuClass
     */
    private class PauseActionPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (mainMenu.getCurrentGame() != null && !escPressed){
                escPressed = true;
                if (!getCurrentGame().isGamePaused()){
                    getCurrentGame().pauseGame();
                }
                else if (getCurrentGame().isGamePaused()) {
                    getCurrentGame().unPauseGame();
                }
            }
        }
    }

    private class PauseActionReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e){
            escPressed = false;
        }
    }

    private Game getCurrentGame() {
        return mainMenu.getCurrentGame();
    }
}