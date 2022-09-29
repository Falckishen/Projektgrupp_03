package View;

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
        //topPanel.setSize(size.width, size.height/3*2);
        JLabel text = new JLabel("hej");
        topPanel.add(text);
        add(topPanel,0);

        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(new Color(0,0,0,0));
        add(middlePanel,1);
        JPanel middlePanel1 = new JPanel();
        middlePanel1.setBackground(new Color(0,0,0,0));
        add(middlePanel1,2);

        JPanel botPanel = new JPanel();
        botPanel.setBackground(Color.yellow);
        //botPanel.setSize(size.width,size.height/3);
        botPanel.setLayout(new GridLayout(0,2));

        JPanel botLeft = new JPanel();
        botLeft.setBackground(Color.GRAY);
        Label quitLabel = new Label("Quit");

        botLeft.add(quitLabel);
        botPanel.add(botLeft);

        JPanel botRight = new JPanel();
        botRight.setBackground(Color.DARK_GRAY);
        botPanel.add(botRight);

        /*
        JButton button1 = new JButton(buttonPressed);
        //button1.setSize(200,200);
        button1.setBackground(Color.BLUE);
        button1.setBorder(null);
        JLabel text2 = new JLabel();
        text2.setText("Press me");
        button1.add(text2);
        botPanel.add(button1);

        botPanel.add(new JButton("Button 1"));
        botPanel.add(new JButton("Button 2"));
        //botPanel.add(new JButton("Button 3"));
         */

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
