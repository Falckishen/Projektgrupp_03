package Controller;

import javax.swing.*;

public class Controller extends JFrame {
    int y, x;
    public Controller() {
        addKeyListener(new KeyboardInput());
        setTitle("Add window title");
        setSize(800, 700);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x = 150;
        y = 150;
    }

    public static void main(String[] args) {
        new Controller();
        //Bara för att testa input, fri att ta bort
    }
}
