package smartbox;

import mvc.Model;
import mvc.View;

public class ContainerView extends View {

    private java.awt.List components;

    public ContainerView(Model model) {
        super(model);
        components = new java.awt.List(10);
        this.add(components, "West");
    }

    //**needs an update method, go to components list and update it when add and remove
    //...using pub sub...
    // etc.

    @Override
    public void update(String message) {
        components.removeAll();
        Container container = (Container) model;
        for(Component component:container.getComponents()){
            components.add(component.toString());
        }
    }
}
