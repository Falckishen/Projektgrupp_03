package View.FramesAndPanels;
import Utilities.LabelButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Alfred Berglöf
 */

public class DeathMenuPanel extends JPanel implements PanelInterface {
    private JFrame parentFrame;
    private void setUpParts(){
        this.setLayout(null);
        int TITLE_SIZE = 80;
        int OPTIONS_SIZE = 40;
        int SCORE_SIZE = 60;
        int score = 420;
        int highScore = 999;
        Action backPressed = new backPressed();
        Action retryPressed = new retryPressed();

        JLabel title = new JLabel("Yah dead", JLabel.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, TITLE_SIZE));
        title.setBounds(0,0, GSW(1),GSH(0.2));
        title.setForeground(Color.BLACK);
        add(title, BorderLayout.CENTER);
        title.setVisible(true);

        JLabel highScoreLabel = new JLabel("Highscore: "+ highScore, JLabel.CENTER);
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


        LabelButton resumeButton = new LabelButton(retryPressed, "Retry",new Font(Font.SANS_SERIF, Font.BOLD, OPTIONS_SIZE),Color.BLACK,Color.red);
        resumeButton.setHorizontalAlignment(JLabel.CENTER);
        resumeButton.setBounds(0, GSH(0.70), GSW(1), GSH(0.1));
        add(resumeButton, BorderLayout.CENTER);
        resumeButton.setVisible(true);

        LabelButton quitButton = new LabelButton(backPressed, "Exit to Menu",new Font(Font.SANS_SERIF, Font.BOLD, OPTIONS_SIZE),Color.BLACK,Color.red);
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

    //TODO add/call method for going back to main menu in actionPerformed()
    static class backPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Back");
        }
    }
    //TODO add/call method for resuming game in actionPerformed()
    static class retryPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Retry");

        }
    }
}
