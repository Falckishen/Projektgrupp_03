package View;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class myTempMain {
    public static void main(String[] args){
        Dimension windowSize = new Dimension(1000,800);
        JFrame frame = new JFrame();
        frame.setSize(windowSize);
        frame.setBackground(Color.red);
        frame.add(new PauseMenuPanel(windowSize));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
}
