package View.FramesAndPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainFrame extends JFrame {

    PanelInterface subPanel;

    public MainFrame(int width, int height){
        setSize(new Dimension(width,height));
        setResizable(false);
        setTitle("(╯°□°）╯︵ ┻━┻");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    public void replaceSubPanel(PanelInterface newPanel){
        if(subPanel != null){
            subPanel.destroyPanel();
        }
        this.subPanel = newPanel;
        subPanel.startPanel(this);
    }

    public void refreshScreen(){
        subPanel.updateDisplay();
    }
}
