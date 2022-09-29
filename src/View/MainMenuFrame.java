package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame {
    private JLabel title;
    private JSlider difficultySlider;
    private JButton startGameButton;
    private JButton endGameButton;
    boolean close = false;
    boolean start = false;

    public MainMenuFrame(){
        setSize(1000, 800);
        setResizable(false);
        setTitle("(╯°□°）╯︵ ┻━┻");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setFocusable(true);
        requestFocus();
        setLayout(null);

        setUpParts();

        setVisible(true);
    }

    private void setUpParts(){
        title = new JLabel("Game Name", JLabel.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
        title.setBounds(0,0,1000,100);
        add(title, BorderLayout.CENTER);
        title.setVisible(true);

        difficultySlider = new JSlider(1, 10);
        difficultySlider.setBounds(300,300,400,100);
        difficultySlider.setValue(1);
        add(difficultySlider, BorderLayout.CENTER);
        difficultySlider.setVisible(true);

        startGameButton = new JButton("Start Game");
        startGameButton.setBounds(300,500,400,100);
        add(startGameButton, BorderLayout.CENTER);
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start = true;
                close = true;
            }
        });
        startGameButton.setVisible(true);

        endGameButton = new JButton("Close");
        endGameButton.setBounds(300,650,400,100);
        add(endGameButton, BorderLayout.CENTER);
        endGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start = false;
                close = true;
            }
        });
        endGameButton.setVisible(true);
    }

    public int difficulty(){
        return(difficultySlider.getValue());
    }
}
