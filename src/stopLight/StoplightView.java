package stopLight;

import mvc.*;
import java.awt.*;

public class StoplightView extends View {

    public StoplightView(Model light) {
        super(light);
        this.setPreferredSize(new Dimension(300,300));
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);

        Color oldColor = gc.getColor();
        Stoplight light = (Stoplight)model;
        StoplightShape shape = new StoplightShape(light);
        shape.draw((Graphics2D) gc);
        gc.setColor(oldColor);


    }
}
