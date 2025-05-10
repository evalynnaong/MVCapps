package smartbox;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

import javax.swing.*;
import java.util.Objects;

public class ContainerFactory implements AppFactory {

    public Model makeModel() {
        return new Container();
    }

    public View makeView(Model m) {
        return new ContainerView(m);
    }

    public String getTitle() {
        return "Smart Box";
    }

    public String getHelp() {
        return "Add to run component \nRun to run container \nRem to remove component";
    }

    public String about() {
        return "Smart Box version 1.0. Evalynna Ong";
    }

    public String[] getEditCommands() {
        String[] eComm = {"add", "run", "rem"};
        return eComm;
    }

    public Command makeEditCommand(Model m, String name, Object source) {
        if(Objects.equals(name, "add")) {
            String input = JOptionPane.showInputDialog("Add component: ");
            return new SBCommand1(m, input);
        }
        else if(Objects.equals(name, "rem")) {
            String input = JOptionPane.showInputDialog("Remove component: ");
            return new SBCommand2(m, input);
        }
        else if(Objects.equals(name, "run")) {
            return new SBCommand3(m);
        }

        return null;
    }
}

