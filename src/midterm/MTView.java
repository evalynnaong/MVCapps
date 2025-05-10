package midterm;

import mvc.Model;
import mvc.View;

import javax.swing.*;

public class MTView extends View {
    private JLabel lab = new JLabel("Accumulator");
    private JTextField calc;
    private MTModel mtmodel;

    public MTView(Model model) {
        super(model);
        this.mtmodel = (MTModel) model;
        int acc = mtmodel.getAccumulator();
        String input = String.valueOf(acc);
        calc = new JTextField(input, 16);
        add(lab, "Center");
        add(calc, "East");


    }

    @Override
    public void update(String message) {
        int acc = mtmodel.getAccumulator();
        String input = String.valueOf(acc);
        calc.setText(input);
    }
}
