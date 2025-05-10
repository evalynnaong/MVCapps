package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// AppPanel is the MVC controller
public class AppPanel extends JPanel implements Subscriber, ActionListener  {

    protected Model model;
    protected AppFactory factory;
    protected View view;
    protected JPanel controlPanel;
    private JFrame frame;
    public static int FRAME_WIDTH = 650;
    public static int FRAME_HEIGHT = 500;

    public AppPanel(AppFactory factory) {

        // initialize fields here
        this.factory = factory;
        this.model = factory.makeModel();
        this.view = factory.makeView(model);
        //this.controlPanel = new JPanel();
        //controlPanel.setLayout(new GridLayout(4,3));

        this.setLayout(new BorderLayout());
        //this.add(controlPanel, BorderLayout.WEST);

        //controlPanel.setPreferredSize(new Dimension(50,50));
        //controlPanel.setBackground(Color.CYAN);
        this.add(view, BorderLayout.EAST);

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);

        frame.setJMenuBar(createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        this.revalidate();
        this.repaint();
        frame.setVisible(true);

    }

    public void display() {
        frame.setVisible(true);
        System.out.println("Components inside AppPanel:");
        for (Component c : this.getComponents()) {
            System.out.println(" - " + c.getClass().getSimpleName());
        }
    }

    public void update() {  /* override in extensions if needed */ }

    public Model getModel() { return model; }

    // called by file/open and file/new
    public void setModel(Model newModel) {
        System.out.println("setModel in appPanel')");
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        // view must also unsubscribe then resubscribe:
        this.view.setModel(this.model);
        model.changed();

        model.setUnsavedChanges(false);

        //this.revalidate();
        //this.repaint();
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[] {"New",  "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu =
                Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[] {"About", "Help"}, this);
        result.add(helpMenu);

        return result;
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String cmmd = ae.getActionCommand();

            if (cmmd.equals("Save")) {
                Utilities.save(model, false);
            } else if (cmmd.equals("SaveAs")) {
                Utilities.save(model, true);
            } else if (cmmd.equals("Open")) {
                Model newModel = Utilities.open(model);
                if (newModel != null) setModel(newModel);
            } else if (cmmd.equals("New")) {
                System.out.println("Trying to make a new");
                Utilities.saveChanges(model);
                setModel(factory.makeModel());
                // needed cuz setModel sets to true:
                model.setUnsavedChanges(false);
            } else if (cmmd.equals("Quit")) {
                Utilities.saveChanges(model);
                System.exit(0);
            } else if (cmmd.equals("About")) {
                Utilities.inform(factory.about());
            } else if (cmmd.equals("Help")) {
                Utilities.inform(factory.getHelp());
            } else { // must be from Edit menu
                //???
                Command a1 = this.factory.makeEditCommand(model, cmmd, ae.getSource());
                a1.execute();
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }

    public void update(String message) {}
}
