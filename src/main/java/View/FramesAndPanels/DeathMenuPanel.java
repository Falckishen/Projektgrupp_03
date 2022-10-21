package View.FramesAndPanels;

import java.awt.*;
import javax.swing.*;
import View.LabelButton;

/**
 * @author Alfred Berglöf
 */

public class DeathMenuPanel extends JPanel implements PanelInterface {
    private JFrame parentFrame;
    private final Action retryListener;
    private final Action exitListener;
    private final int highscore;
    private final int score;

    public DeathMenuPanel(Action retryListener, Action exitListener, int highscore, int score) {
        this.retryListener = retryListener;
        this.exitListener = exitListener;
        this.highscore = highscore;
        this.score = score;
    }

    private void setUpParts(){
        this.setLayout(null);
        int TITLE_SIZE = 80;
        int OPTIONS_SIZE = 40;
        int SCORE_SIZE = 60;

        JLabel title = new JLabel("Yah dead lmao", JLabel.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, TITLE_SIZE));
        title.setBounds(0,0, GSW(1),GSH(0.2));
        title.setForeground(Color.BLACK);
        add(title, BorderLayout.CENTER);
        title.setVisible(true);

        JLabel highScoreLabel = new JLabel("Highscore: "+ highscore, JLabel.CENTER);
        highScoreLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, OPTIONS_SIZE));
        highScoreLabel.setBounds(0,GSH(0.30), GSW(1),GSH(0.1));
        highScoreLabel.setForeground(Color.BLACK);
        add(highScoreLabel, BorderLayout.CENTER);
        highScoreLabel.setVisible(true);

        JLabel scoreLabel = new JLabel("Your score: "+ score, JLabel.CENTER);
        scoreLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, SCORE_SIZE));
        scoreLabel.setBounds(0,GSH(0.40), GSW(1),GSH(0.1));
        scoreLabel.setForeground(Color.BLACK);
        add(scoreLabel, BorderLayout.CENTER);
        scoreLabel.setVisible(true);

        LabelButton resumeButton = new LabelButton(retryListener, "Retry",new Font(Font.SANS_SERIF, Font.BOLD, OPTIONS_SIZE),Color.BLACK,Color.red);
        resumeButton.setHorizontalAlignment(JLabel.CENTER);
        resumeButton.setBounds(0, GSH(0.70), GSW(1), GSH(0.1));
        add(resumeButton, BorderLayout.CENTER);
        resumeButton.setVisible(true);

        LabelButton quitButton = new LabelButton(exitListener, "Exit to Menu",new Font(Font.SANS_SERIF, Font.BOLD, OPTIONS_SIZE),Color.BLACK,Color.red);
        quitButton.setHorizontalAlignment(JLabel.CENTER);
        quitButton.setBounds(GSW(0), GSH(0.80), GSW(1), GSH(0.1));
        add(quitButton,BorderLayout.CENTER);
        quitButton.setVisible(true);
    }

    @Override
    public void startPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setSize(parentFrame.getWidth(), parentFrame.getHeight());
        parentFrame.add(this);
        setVisible(true);
        setUpParts();
    }

    @Override
    public void updateDisplay() {
        parentFrame.repaint();
    }

    @Override
    public void destroyPanel() {
        getParent().remove(this);
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
}