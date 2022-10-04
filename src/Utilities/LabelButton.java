package Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LabelButton extends JLabel{
    private Color color;
    private Color highlightColor;
    private JLabel text;
    private Action buttonPressed;

    public LabelButton(Action a, String buttonText, Font textFont, Color textColor, Color highlightColor){
        color = textColor;
        this.highlightColor = highlightColor;
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
            text.setForeground(highlightColor);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("exited");
            text.setForeground(color);
        }
    }
}
