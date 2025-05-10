package smartbox;

import mvc.Command;
import mvc.Model;

public class SBCommand1 extends Command {
    private Container container;
    private String componentName;

    public SBCommand1(Model model, String componentName) {
        super(model);
        this.container = (Container) model;
        this.componentName = componentName;
    }

    @Override
    public void execute() throws Exception{
        if(container == null) {
            throw new Exception("model is missing field");
        }
        container.addComponent(componentName);
    }
}
