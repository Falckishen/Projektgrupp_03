package View.FramesAndPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel implements PanelInterface{

    private JFrame parentFrame;

    private JLabel title;
    private JButton startGameButton;
    private JButton endGameButton;
    boolean close = false;
    boolean start = false;

    public MainMenuPanel(){}

    private void setUpParts(){
        title = new JLabel("Don't die, or else", JLabel.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
        title.setBounds(0,0,1000,100);
        add(title, BorderLayout.CENTER);
        title.setVisible(true);

        startGameButton = new JButton("Start Game");
        startGameButton.setBounds(300,500,400,100);
        add(startGameButton, BorderLayout.CENTER);
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start = true;
                close = true;
            }
        });
        startGameButton.setVisible(true);

        endGameButton = new JButton("Close");
        endGameButton.setBounds(300,650,400,100);
        add(endGameButton, BorderLayout.CENTER);
        endGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start = false;
                close = true;
            }
        });
        endGameButton.setVisible(true);
    }

    @Override
    public void startPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setSize(parentFrame.getWidth(), parentFrame.getHeight());
        setUpParts();
        setVisible(true);
    }

    @Override
    public void updateDisplay() {
        //repaint();
    }

    @Override
    public void destroyPanel() {
        getParent().remove(this);
    }
}
