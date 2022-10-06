package View.FramesAndPanels;

import Utilities.LabelButton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseMenuPanel extends JPanel implements PanelInterface{
    private JFrame parentFrame;

    public PauseMenuPanel(){}

    private void setUpParts(){
        this.setLayout(null);
        Color translucent = new Color(0,0,0,0);
        int TITLE_SIZE = 80;
        int OPTIONS_SIZE = 60;

        //this.setBackground(new Color(0,0,0,200));

        JLabel title = new JLabel("PAUSED", JLabel.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, TITLE_SIZE));
        title.setBounds(0,0, GSW(1),GSH(0.125));
        add(title, BorderLayout.CENTER);
        title.setVisible(true);

        JButton quitButton = new JButton("Exit to Menu");
        quitButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        quitButton.setBounds(GSW(0.15), GSH(0.85), GSW(0.3), GSH(0.1));
        add(quitButton, BorderLayout.CENTER);
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("quit");
            }
        });
        quitButton.setVisible(true);

        JButton resumeButton = new JButton("Resume");
        resumeButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        resumeButton.setBounds(GSW(0.55), GSH(0.85), GSW(0.3), GSH(0.1));
        add(resumeButton, BorderLayout.CENTER);
        resumeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("resume");
            }
        });
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

    //TODO add/call method for quiting/giving up game in actionPerformed()
    static class quitPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Quitting");
        }
    }
    //TODO add/call method for resuming game in actionPerformed()
    static class resumePressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Resuming");

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
}