package Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LabelButton extends JLabel{
    private Color color;
    private JLabel text;
    private Action buttonPressed;

    public LabelButton(Action a, String buttonText, Font textFont, Color textColor){
        color = textColor;
        buttonPressed = a;
        this.setText(buttonText);
        this.setFont(textFont);
        this.setForeground(color);
        this.addMouseListener(new mouseHighlight());
        text = this;
    }

    class mouseHighlight extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("clicked");
            buttonPressed.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("entered");
            text.setForeground(new Color(9,205,218));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("exited");
            text.setForeground(color);
        }
    }
}
