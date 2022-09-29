package View.FramesAndPanels;

import javax.swing.*;

interface PanelInterface {
    void startPanel(JFrame parentFrame);

    void updateDisplay();

    void destroyPanel();
}
