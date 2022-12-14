package View.FramesAndPanels;

import java.awt.*;
import javax.swing.*;

/**
 * MainFrame represents the window and should be coupled with an implementation of PanelInterface.
 */
public class MainFrame extends JFrame {

    private PanelInterface subPanel;

    /**
     * @param width The width of the frame
     * @param height The height of the frame
     */
    public MainFrame(int width, int height){
        setSize(new Dimension(width,height));
        setResizable(false);
        setTitle("(╯°□°）╯︵ ┻━┻");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    /**
     * Used to replace the current subPanel with a new one, destroys the old one.
     * @param newPanel The new panel to attach
     */
    public void replaceSubPanel(PanelInterface newPanel){
        if(subPanel != null){
            subPanel.destroyPanel();
        }
        this.subPanel = newPanel;
        subPanel.startPanel(this);
    }

    /**
     * Refreshes the display by telling the supPanel to update.
     */
    public void refreshScreen(){
        subPanel.updateDisplay();
    }
}
