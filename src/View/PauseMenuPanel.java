package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PauseMenuPanel extends JPanel {
    private Action buttonPressed;
    private JLabel resumeLabel;
    private JLabel quitLabel;
    public PauseMenuPanel(Dimension size){
        buttonPressed = new ButtonPressed();

        setSize(size);
        setBackground(new Color(0,0,0,200));
        setLayout(new GridLayout(4,0));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.GREEN);
        //topPanel.setSize(size.width, size.height/3*2);
        JLabel title = new JLabel("Paused");
        title.setFont(new Font("Sans Serif", Font.PLAIN, 60));
        topPanel.setAlignmentY(FlowLayout.CENTER);
        topPanel.add(title);
        add(topPanel,0);

        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(new Color(0,0,0,0));
        add(middlePanel,1);
        JPanel middlePanel1 = new JPanel();
        middlePanel1.setBackground(new Color(0,0,0,0));
        add(middlePanel1,2);

        JPanel botPanel = new JPanel();
        botPanel.setBackground(Color.yellow);
        //botPanel.setSize(size.width,size.height/3);
        botPanel.setLayout(new GridLayout(0,2));

        JPanel botLeft = new JPanel();
        botLeft.setBackground(Color.GRAY);
        quitLabel = new JLabel("Quit");
        quitLabel.setFont(new Font("Sans Serif", Font.PLAIN, 50));
        quitLabel.addMouseListener(new mouseHighlight());

        botLeft.add(quitLabel);
        botPanel.add(botLeft);

        JPanel botRight = new JPanel();
        botRight.setBackground(Color.DARK_GRAY);
        resumeLabel = new JLabel("Resume");
        resumeLabel.setFont(new Font("Sans Serif", Font.PLAIN, 50));
        resumeLabel.addMouseListener(new mouseHighlight());

        botRight.add(resumeLabel);
        botPanel.add(botRight);

        /*
        JButton button1 = new JButton(buttonPressed);
        //button1.setSize(200,200);
        button1.setBackground(Color.BLUE);
        button1.setBorder(null);
        JLabel text2 = new JLabel();
        text2.setText("Press me");
        button1.add(text2);
        botPanel.add(button1);

        botPanel.add(new JButton("Button 1"));
        botPanel.add(new JButton("Button 2"));
        //botPanel.add(new JButton("Button 3"));
         */

        add(botPanel,3);
        setVisible(true);
    }

    class mouseHighlight extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("clicked");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("entered");
            resumeLabel.setForeground(new Color(9,205,218));
            quitLabel.setForeground(new Color(9,205,218));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("exited");
            resumeLabel.setForeground(Color.BLACK);
            quitLabel.setForeground(Color.BLACK);
        }
    }
    class ButtonPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("I am pressed");
        }
    }
}
