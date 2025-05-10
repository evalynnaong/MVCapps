package mvc;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel implements Subscriber{
    protected Model model;

    public View(Model model){
        this.model = model;
        model.subscribe(this);
        repaint();
    }

    public void update(String message) {
        repaint();
    }

    public void setModel(Model newModel) {
        model.unsubscribe(this);
        this.model = newModel;
        model.subscribe(this);

        update("");
        repaint();
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
    }
}
