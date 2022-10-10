package View.FramesAndPanels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenuPanel extends JPanel implements PanelInterface{

    private JFrame parentFrame;
    private final ActionListener startListener;
    private final ActionListener quitListener;
    private final ActionListener firstListener;
    private final ActionListener secondListener;
    private final ActionListener thirdListener;
    private String gameName;
    private int highscore;

    public MainMenuPanel(ActionListener acStart, ActionListener acQuit, ActionListener firstListener, ActionListener secondListener, ActionListener thirdListener, String gameName, int highscore){
        this.startListener = acStart;
        this.quitListener = acQuit;
        this.firstListener = firstListener;
        this.secondListener = secondListener;
        this.thirdListener = thirdListener;
        this.gameName = gameName;
        this.highscore = highscore;
    }


    private void setUpParts(){
        this.setLayout(null);
        JLabel title = new JLabel(gameName, JLabel.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
        title.setBounds(0,0, GSW(1),GSH(0.125));
        add(title, BorderLayout.CENTER);
        title.setVisible(true);

        JLabel score;
        score = new JLabel(highscore + "", JLabel.CENTER);
        score.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        score.setBounds(GSW(0.15), GSH(0.5) ,GSW(0.7), GSH(0.05));
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
        firstDifficulty.setBounds(GSW(0.15), GSH(0.75) ,GSW(0.10), GSH(0.05));
        secondDifficulty.setBounds(GSW(0.45), GSH(0.75) ,GSW(0.10), GSH(0.05));
        thirdDifficulty.setBounds(GSW(0.75), GSH(0.75) ,GSW(0.10), GSH(0.05));
        firstDifficulty.setText("First");
        secondDifficulty.setText("Second");
        thirdDifficulty.setText("Third");
        add(firstDifficulty, BorderLayout.CENTER);
        firstDifficulty.setVisible(true);
        add(secondDifficulty, BorderLayout.CENTER);
        secondDifficulty.setVisible(true);
        add(thirdDifficulty, BorderLayout.CENTER);
        thirdDifficulty.setVisible(true);
        firstDifficulty.addActionListener(firstListener);
        secondDifficulty.addActionListener(secondListener);
        thirdDifficulty.addActionListener(thirdListener);

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

    private int GSW(double scale){
        return(getScaledWidth(scale));
    }

    private int GSH(double scale){
        return(getScaledHeight(scale));
    }

    private int getScaledWidth(double scale){
        return((int)Math.round(parentFrame.getWidth()*scale));
    }

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
