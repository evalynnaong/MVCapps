package midterm;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

import javax.swing.*;
import java.util.Objects;

public class MTAppFactory implements AppFactory {
    public Model makeModel() {
        System.out.println("should make new model");
        return new MTModel();
    }

    public View makeView(Model m) {
        System.out.println("should make new view");
        return new MTView(m);
    }

    public String getTitle() {
        return "Midterm App";
    }

    public String getHelp() { // rewrite according to midterm
        return "Click direction buttons to navigate Mine Field \n" +
                "Try to avoid mines\n" +
                "Please extend screen to view buttons";
    }

    public String about() { // rewrite acc to midterm
        return "Mine Field Game version 1.0. Copyright 2025 Group 6";
    }

    public String[] getEditCommands() { // add whatever commands
        String[] eComm = {"add", "mul", "clear"};
        return eComm;
    }

    public Command makeEditCommand(Model m, String cmmd, Object source) { //MISSING Object source like in the example
        if(Objects.equals(cmmd, "add")) {
            String input = JOptionPane.showInputDialog("number: ");
            return new MTCommand1(m, Integer.parseInt(input)); // some command for midterm
        }
        else if (Objects.equals(cmmd, "mul")) {
            String input = JOptionPane.showInputDialog("number: ");
            return new MTCommand2(m, Integer.parseInt(input));
        }
        else if (Objects.equals(cmmd, "clear")) {
            return new MTCommand3(m);
        }
        return null;
    }
}
