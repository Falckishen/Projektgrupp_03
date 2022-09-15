package Controller;

import javax.swing.*;
import java.awt.*;

public class tempClassForTesting {

    public static void main(String[] args ){
        JFrame frame = new JFrame("KeyBinding Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);

        JLabel label = new JLabel();
        label.setBackground(Color.red);
        label.setBounds(100, 100, 100, 100);
        label.setOpaque(true);

        //newKeyboardInput game = new newKeyboardInput(label);

        frame.add(label);
        frame.setVisible(true);
    }
}
