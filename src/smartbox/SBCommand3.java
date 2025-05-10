package smartbox;

import mvc.Command;
import mvc.Model;

import java.util.Collection;

public class SBCommand3 extends Command {
    private Container container;


    public SBCommand3(Model model) {
        super(model);
        this.container = (Container) model;
    }

    @Override
    public void execute() throws Exception {
        if(container == null) {
            throw new Exception("model is missing field");
        }

        Collection<Component> components = container.getComponents();

        for(Component component : components) {
            container.launch(component.toString());
        }
    }
}
