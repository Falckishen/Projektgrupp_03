package View.FramesAndPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel implements PanelInterface{

    private JFrame parentFrame;

    private JLabel title;
    private JLabel[] scoreBoard;
    private JButton startGameButton;
    private JButton endGameButton;
    boolean close = false;
    boolean start = false;

    public MainMenuPanel(){}


    private void setUpParts(){
        this.setLayout(null);
        title = new JLabel("Don't die, or else", JLabel.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
        title.setBounds(0,0, GSW(1),GSH(0.125));
        add(title, BorderLayout.CENTER);
        title.setVisible(true);

        scoreBoard = new JLabel[10];
        JLabel score;
        for(int i=0; i < 10; i++){
            score = new JLabel("100", JLabel.CENTER);
            score.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
            score.setBounds(GSW(0.15), GSH(0.15+0.05*i) ,GSW(0.7), GSH(0.05));
            if(i%2 == 0){
                score.setBackground(Color.WHITE);
            }else{
                score.setBackground(Color.LIGHT_GRAY);
            }
            score.setOpaque(true);
            add(score, BorderLayout.CENTER);
            score.setVisible(true);
            scoreBoard[i] = score;
        }

        JPanel borderPanel = new JPanel();
        borderPanel.setBounds(GSW(0.13), GSH(0.13) ,GSW(0.74), GSH(0.54));
        borderPanel.setBackground(Color.GRAY);
        add(borderPanel, BorderLayout.CENTER);
        borderPanel.setVisible(true);

        startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        startGameButton.setBounds(GSW(0.55), GSH(0.85), GSW(0.3), GSH(0.1));
        add(startGameButton, BorderLayout.CENTER);
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start = true;
                close = true;
            }
        });
        startGameButton.setVisible(true);

        endGameButton = new JButton("Close");
        endGameButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        endGameButton.setBounds(GSW(0.15), GSH(0.85), GSW(0.3), GSH(0.1));
        add(endGameButton, BorderLayout.CENTER);
        endGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start = false;
                close = true;
            }
        });
        endGameButton.setVisible(true);

        String[] names = {"Alpha", "Bravo", "Charlie", "Delta", null};
        int[] scores = {5, 4, 3, 0, 1, 1};
        setScoreBoard(names, scores);
    }

    public void setScoreBoard(String[] names, int[] score){
        for(int i=0; i < scoreBoard.length; i++){
            if(i < names.length && i < score.length){
                if(names[i] != null && score[i] != 0){
                    scoreBoard[i].setText(names[i] + ": " + score[i]);
                }else{
                    scoreBoard[i].setText("Error: name is null or score is 0");
                }
            }else{
                if(names.length != score.length){
                    scoreBoard[i].setText("Error: more names than scores or vice versa");
                }else{
                    scoreBoard[i].setText(" ");
                }
            }
        }
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