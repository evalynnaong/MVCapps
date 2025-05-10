package stopLight;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;
import java.awt.*;


public class StoplightPanel extends AppPanel {
    private JButton change;
    public StoplightPanel(AppFactory factory) {
        super(factory);

        this.setLayout(new BorderLayout());

        change = new JButton("Change");
        change.addActionListener(this);
        System.out.println("adding button:");
        controlPanel.add(change);

        this.add(view, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();

    }


    public static void main(String[] args) {
        AppFactory factory = new StoplightFactory();
        AppPanel panel = new StoplightPanel(factory);
        panel.display();
    }

}
