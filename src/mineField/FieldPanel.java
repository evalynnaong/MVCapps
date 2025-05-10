package mineField;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;
import java.awt.*;

public class FieldPanel extends AppPanel {
    //private JPanel keys;

    JButton b1 = new JButton("N");
    JButton b2 = new JButton("S");
    JButton b3 = new JButton("E");
    JButton b4 = new JButton("W");
    JButton b5 = new JButton("NE");
    JButton b6 = new JButton("NW");
    JButton b7 = new JButton("SE");
    JButton b8 = new JButton("SW");

    public FieldPanel(AppFactory factory) {
        super(factory);
        controlPanel.setLayout(new GridLayout(4,3));

        b1.addActionListener(this);
        controlPanel.add(b1);

        b2.addActionListener(this);
        controlPanel.add(b2);

        b3.addActionListener(this);
        controlPanel.add(b3);

        b4.addActionListener(this);
        controlPanel.add(b4);

        b5.addActionListener(this);
        controlPanel.add(b5);

        b6.addActionListener(this);
        controlPanel.add(b6);

        b7.addActionListener(this);
        controlPanel.add(b7);

        b8.addActionListener(this);
        controlPanel.add(b8);

    }

    public static void main(String[] args) {
        FieldFactory factory = new FieldFactory();
        AppPanel panel = new FieldPanel(factory);
        panel.display();
    }
}
