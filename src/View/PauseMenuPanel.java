package View;

import Utilities.LabelButton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PauseMenuPanel extends JPanel {
    public PauseMenuPanel(Dimension size){
        Color translucent = new Color(0,0,0,0);
        int TITLE_SIZE = 80;
        int OPTIONS_SIZE = 60;

        this.setSize(size);
        this.setBackground(new Color(0,0,0,200));
        this.setLayout(new GridLayout(4,0));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(translucent);
        JLabel title = new JLabel("PAUSED");
        title.setFont(new Font("Sans Serif", Font.PLAIN, TITLE_SIZE));
        title.setForeground(Color.BLACK);
        topPanel.add(title);
        this.add(topPanel,0);

        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(translucent);
        this.add(middlePanel,1);
        JPanel middlePanel1 = new JPanel();
        middlePanel1.setBackground(translucent);
        this.add(middlePanel1,2);

        JPanel botPanel = new JPanel();
        botPanel.setBackground(translucent);
        botPanel.setLayout(new GridLayout(0,2));

        JPanel botLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botLeft.setBackground(translucent);

        LabelButton quitLabel = new LabelButton(new quitPressed(), "Exit to Menu", new Font("Sans Serif", Font.PLAIN, OPTIONS_SIZE), Color.BLACK, new Color(9,205,218));
        botLeft.setBorder(new EmptyBorder((int)(size.getHeight()/4/100)*40,(int)(size.getWidth()/2/100)*15,0,0));
        botLeft.add(quitLabel);

        botPanel.add(botLeft);

        JPanel botRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botRight.setBackground(translucent);

        LabelButton resumeLabel = new LabelButton(new resumePressed(), "Resume", new Font("Sans Serif", Font.PLAIN, OPTIONS_SIZE), Color.BLACK, new Color(9,205,218));
        botRight.setBorder(new EmptyBorder((int)(size.getHeight()/4/100)*40,0,0,(int)(size.getWidth()/2/100)*15));
        botRight.add(resumeLabel);

        botPanel.add(botRight);

        this.add(botPanel,3);
        this.setVisible(true);
    }

    //TODO add/call method for quiting/giving up game in actionPerformed()
    static class quitPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Quitting");
        }
    }
    //TODO add/call method for resuming game in actionPerformed()
    static class resumePressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Resuming");

        }
    }
}
