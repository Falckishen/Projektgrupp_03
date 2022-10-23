package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Custom button for the user interface
 * @author Alfred Berglöf
 */
public class LabelButton extends JLabel{

    private final Color color;
    private final Color highlightColor;
    private final JLabel text;
    private final Action buttonPressed;

    /**
     * @param a What happens when the user clicks the button.
     * @param buttonText What the button should say
     * @param textFont The font of the button
     * @param textColor The color of the button
     * @param highlightColor The highlightcolor of the button
     */
    public LabelButton(Action a, String buttonText, Font textFont, Color textColor, Color highlightColor){
        color = textColor;
        this.highlightColor = highlightColor;
        buttonPressed = a;
        this.setText(buttonText);
        this.setFont(textFont);
        this.setForeground(color);
        this.setBackground(color);
        this.addMouseListener(new mouseHighlight());
        text = this;
    }

    class mouseHighlight extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            buttonPressed.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            text.setForeground(highlightColor);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            text.setForeground(color);
        }
    }
}
