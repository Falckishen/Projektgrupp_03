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
        setLayout(new GridLayout(3,3));

        JButton button1 = new JButton(buttonPressed);
        //button1.setSize(200,200);
        button1.setBackground(Color.BLUE);

        JLabel text = new JLabel();
        text.setText("Press me");
        button1.add(text);

        add(button1);
        add(button1);
        add(button1);

        setVisible(true);
    }

    class ButtonPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("I am pressed");
        }
    }
}
