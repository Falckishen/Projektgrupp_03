package View.FramesAndPanels;

import View.LabelButton;
import javax.swing.*;
import java.awt.*;

/**
 * @author Alfred Berglöf and William Johansson
 */
public class PauseMenuPanel extends JPanel implements PanelInterface{
    private JFrame parentFrame;
    private final Action resumeListener;
    private final Action forfeitListener;

    public PauseMenuPanel(Action resumeListener, Action forfeitListener){
        this.resumeListener = resumeListener;
        this.forfeitListener = forfeitListener;
    }

    private void setUpParts(){
        this.setLayout(null);
        int TITLE_SIZE = 80;
        int OPTIONS_SIZE = 40;

        JLabel title = new JLabel("PAUSED", JLabel.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, TITLE_SIZE));
        title.setBounds(0,0, GSW(1),GSH(0.125));
        title.setForeground(Color.BLACK);
        add(title, BorderLayout.CENTER);
        title.setVisible(true);

        LabelButton quitButton = new LabelButton(forfeitListener, "Give up",new Font(Font.SANS_SERIF, Font.BOLD, OPTIONS_SIZE),Color.BLACK,Color.red);
        quitButton.setBounds(GSW(0.15), GSH(0.85), GSW(0.3), GSH(0.1));
        add(quitButton,BorderLayout.CENTER);
        quitButton.setVisible(true);

        LabelButton resumeButton = new LabelButton(resumeListener, "Resume",new Font(Font.SANS_SERIF, Font.BOLD, OPTIONS_SIZE),Color.BLACK,Color.red);
        resumeButton.setBounds(GSW(0.70), GSH(0.85), GSW(0.3), GSH(0.1));
        add(resumeButton, BorderLayout.CENTER);
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
