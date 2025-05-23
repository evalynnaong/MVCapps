package stopLight;

import mvc.*;

public class ChangeCommand extends Command {
    //private Stoplight light;

    public ChangeCommand(Model model) {
        super(model);
    }

    public void execute() {
        Stoplight light = (Stoplight)model;
        light.change();
    }

}
