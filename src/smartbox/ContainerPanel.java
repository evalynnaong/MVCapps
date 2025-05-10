package smartbox;

import mvc.AppFactory;
import mvc.AppPanel;
import mvc.Model;

import javax.swing.*;

public class ContainerPanel extends AppPanel {
    //java.awt.List components;
    private JPanel controls;
    public ContainerPanel(AppFactory factory) {
        super(factory);
        // **set up controls
        controls = new JPanel();

        JButton addButton = new JButton("add");
        addButton.addActionListener(this);
        controls.add(addButton);

        JButton rem = new JButton("rem");
        rem.addActionListener(this);
        controls.add(rem);

        JButton run = new JButton("run");
        run.addActionListener(this);
        controls.add(run);

        add(controls, "West");
    }

    // this override needed to re-initialize component fields table:
    public void setModel(Model m) {
        super.setModel(m);
        ((Container) m).initContainer(); // restore fields of components

        //*this is from the appPanel class, when create new model go thru new and open cmds; what do those do
        //here need to override it and use initContainer that redoes the parts that were not serializable
    }

    public static void main(String[] args) {
        AppPanel panel = new ContainerPanel(new ContainerFactory());
        panel.display();
    }
}
