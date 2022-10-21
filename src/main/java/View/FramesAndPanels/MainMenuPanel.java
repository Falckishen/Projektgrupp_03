package View.FramesAndPanels;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The panel representing the MainMenu
 */
public class MainMenuPanel extends JPanel implements PanelInterface{

    private JFrame parentFrame;
    private final ActionListener startListener;
    private final ActionListener quitListener;
    private final ActionListener firstListener;
    private final ActionListener secondListener;
    private final ActionListener thirdListener;
    private final String gameName;
    private final int highscore;

    /**
     * @param acStart What happens when user clicks start.
     * @param acQuit What happens when user clicks quit.
     * @param firstListener What happens when user clicks first difficulty.
     * @param secondListener What happens when user clicks second difficulty.
     * @param thirdListener What happens when user clicks third difficulty.
     * @param gameName What the MainMenu title should say.
     * @param highscore What the current highscore is.
     */
    public MainMenuPanel(ActionListener acStart, ActionListener acQuit, ActionListener firstListener, ActionListener secondListener, ActionListener thirdListener, String gameName, int highscore){
        this.startListener = acStart;
        this.quitListener = acQuit;
        this.firstListener = firstListener;
        this.secondListener = secondListener;
        this.thirdListener = thirdListener;
        this.gameName = gameName;
        this.highscore = highscore;
    }

    /**
     * Creates and positions all the buttons and labels onto this panel.
     */
    private void setUpParts(){
        this.setLayout(null);
        JLabel title = new JLabel(gameName, JLabel.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
        title.setBounds(0,0, GSW(1),GSH(0.125));
        add(title, BorderLayout.CENTER);
        title.setVisible(true);

        JLabel score;
        score = new JLabel("Highscore: " + highscore, JLabel.CENTER);
        score.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        score.setBounds(GSW(0.15), GSH(0.5) ,GSW(0.7), GSH(0.10));
        score.setOpaque(true);
        add(score, BorderLayout.CENTER);
        score.setVisible(true);

        ButtonGroup difficultyGroup = new ButtonGroup();
        JRadioButton firstDifficulty = new JRadioButton();
        difficultyGroup.add(firstDifficulty);
        JRadioButton secondDifficulty = new JRadioButton();
        difficultyGroup.add(secondDifficulty);
        JRadioButton thirdDifficulty = new JRadioButton();
        difficultyGroup.add(thirdDifficulty);
        firstDifficulty.setBounds(GSW(0.25), GSH(0.7) ,GSW(0.10), GSH(0.05));
        firstDifficulty.setBackground(new Color(150, 150, 150));
        secondDifficulty.setBounds(GSW(0.45), GSH(0.7) ,GSW(0.10), GSH(0.05));
        secondDifficulty.setBackground(new Color(150, 150, 150));
        thirdDifficulty.setBounds(GSW(0.65), GSH(0.7) ,GSW(0.10), GSH(0.05));
        thirdDifficulty.setBackground(new Color(150, 150, 150));
        firstDifficulty.setText("First");
        firstDifficulty.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        secondDifficulty.setText("Second");
        secondDifficulty.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        thirdDifficulty.setText("Third");
        thirdDifficulty.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        add(firstDifficulty, BorderLayout.CENTER);
        firstDifficulty.setVisible(true);
        add(secondDifficulty, BorderLayout.CENTER);
        secondDifficulty.setVisible(true);
        add(thirdDifficulty, BorderLayout.CENTER);
        thirdDifficulty.setVisible(true);
        firstDifficulty.addActionListener(firstListener);
        secondDifficulty.addActionListener(secondListener);
        thirdDifficulty.addActionListener(thirdListener);
        firstDifficulty.setSelected(true);

        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setBounds(GSW(0.22), GSH(0.67), GSW(0.56), GSH(0.11));
        difficultyPanel.setBackground(new Color(120, 120, 120));
        add(difficultyPanel, BorderLayout.CENTER);
        difficultyPanel.setVisible(true);

        JButton startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        startGameButton.setBounds(GSW(0.55), GSH(0.85), GSW(0.3), GSH(0.1));
        add(startGameButton, BorderLayout.CENTER);
        startGameButton.addActionListener(startListener);
        startGameButton.setVisible(true);

        JButton endGameButton = new JButton("Close");
        endGameButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        endGameButton.setBounds(GSW(0.15), GSH(0.85), GSW(0.3), GSH(0.1));
        add(endGameButton, BorderLayout.CENTER);
        endGameButton.addActionListener(quitListener);
        endGameButton.setVisible(true);
    }

    /**
     * Identical to getScaledWidth() but with more compact name.
     */
    private int GSW(double scale){
        return(getScaledWidth(scale));
    }

    /**
     * Identical to getScaledHeight() but with more compact name.
     */
    private int GSH(double scale){
        return(getScaledHeight(scale));
    }

    /**
     * Gets a point on the width of the panel based on percentage of the width of the panel.
     * @param scale How far to go.
     * @return The point on the width.
     */
    private int getScaledWidth(double scale){
        return((int)Math.round(parentFrame.getWidth()*scale));
    }

    /**
     * Gets a point on the height of the panel based on percentage of the height of the panel.
     * @param scale How far to go.
     * @return The point on the height.
     */
    private int getScaledHeight(double scale){
        return((int)Math.round(parentFrame.getHeight()*scale));
    }

    @Override
    public void startPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setSize(parentFrame.getWidth(), parentFrame.getHeight());
        setUpParts();
        parentFrame.add(this);
        setVisible(true);
    }

    @Override
    public void updateDisplay() {
        parentFrame.repaint();
    }

    @Override
    public void destroyPanel() {
        getParent().remove(this);
    }
}
