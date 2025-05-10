package midterm;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;

public class MTAppPanel extends AppPanel {
    public MTAppPanel(AppFactory factory) {
        super(factory);

        //controlPanel.setLayout(BorderLayout());

        JButton b1 = new JButton("add");
        b1.addActionListener(this);
        controlPanel.add(b1);

        JButton b2 = new JButton("mul");
        b2.addActionListener(this);
        controlPanel.add(b2);

        JButton b3 = new JButton("clear");
        b3.addActionListener(this);
        controlPanel.add(b3);
    }

    public static void main(String[] args) {
        MTAppFactory factory = new MTAppFactory();
        AppPanel panel = new MTAppPanel(factory);
        panel.display();
    }
}
