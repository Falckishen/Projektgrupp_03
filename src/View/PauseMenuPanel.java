package View;

import Utilities.LabelButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PauseMenuPanel extends JPanel {
    private Action buttonPressed;
    public PauseMenuPanel(Dimension size){
        buttonPressed = new ButtonPressed();

        setSize(size);
        setBackground(new Color(0,0,0,200));
        setLayout(new GridLayout(4,0));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.GREEN);
        JLabel title = new JLabel("Paused");
        title.setFont(new Font("Sans Serif", Font.PLAIN, 60));
        topPanel.setAlignmentY(FlowLayout.CENTER);
        topPanel.add(title);
        add(topPanel,0);

        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(new Color(0,0,0,0));
        add(middlePanel,1);
        JPanel middlePanel1 = new JPanel();
        middlePanel1.setBackground(new Color(0,0,0,0));
        add(middlePanel1,2);

        JPanel botPanel = new JPanel();
        botPanel.setBackground(Color.yellow);
        botPanel.setLayout(new GridLayout(0,2));

        JPanel botLeft = new JPanel();
        botLeft.setBackground(Color.GRAY);

        LabelButton quitLabel = new LabelButton(buttonPressed, "Quit", new Font("Sans Serif", Font.PLAIN, 50), Color.BLACK, new Color(9,205,218));
        botLeft.add(quitLabel);

        botPanel.add(botLeft);

        JPanel botRight = new JPanel();
        botRight.setBackground(Color.DARK_GRAY);

        LabelButton resumeLabel = new LabelButton(buttonPressed, "Resume", new Font("Sans Serif", Font.PLAIN, 50), Color.BLACK, new Color(9,205,218));
        botRight.add(resumeLabel);
        resumeLabel.validate();
        botRight.validate();

        botPanel.add(botRight);

        add(botPanel,3);
        setVisible(true);
    }

    class ButtonPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("I am pressed");
        }
    }
}
