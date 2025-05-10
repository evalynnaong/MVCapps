package smartbox;

import mvc.Command;
import mvc.Model;

public class SBCommand2 extends Command {
    private Container container;
    private String componentName;

    public SBCommand2(Model model, String componentName) {
        super(model);
        this.container = (Container) model;
        this.componentName = componentName;
    }

    @Override
    public void execute() throws Exception{
        if(container == null) {
            throw new Exception("model is missing field");
        }
        container.remComponent(componentName);
    }
}
