package View.FramesAndPanels;

import javax.swing.*;

/**
 * The Interface whose methods the MainFrame class expects to be able to call upon panels designated to it.
 */
public interface PanelInterface {

    /**
     * Should make the panel setup itself.
     * @param parentFrame the MainFrame that contains the panel.
     */
    void startPanel(JFrame parentFrame);

    /**
     * Should refresh the panel.
     */
    void updateDisplay();

    /**
     * Should destroy the panel
     */
    void destroyPanel();
}
