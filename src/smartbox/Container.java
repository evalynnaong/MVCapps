package smartbox;

import mvc.Model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Container extends Model {

    // declarations of req tables
    private Map<Class<?>, Component> providedInterfaces = new HashMap<Class<?>, Component>();
    private Map<Class<?>, Component> requiredInterfaces = new HashMap<Class<?>, Component>();
    private Map<String, Component> components = new HashMap<String, Component>();

    public Collection<Component> getComponents() {
        return components.values();
    }

    //example of method overloading!:
    //when click on add button and type in name, this one will get called:
    public void addComponent(String name) throws Exception {
        String qualName = "smartbox.components." +  name;

        //need to use reflection
        Class<?> classComp = Class.forName(qualName);
        Object obj = classComp.getDeclaredConstructor().newInstance();
        // if (!(obj instanceof Component)) throw new Exception("Not a component");
        addComponent((Component)obj);
    }

    //you made the component, now you actually want to add it:
    private void addComponent(Component component) throws Exception {
        //component has pointer to the container it exists in:
        component.setContainer(this);
        // add new guy to the components table:
        components.put(component.name, component);
        // update provided interfaces table:
        for(Class<?> intf: component.getProvidedInterfaces()) {
            providedInterfaces.put(intf,  component);
        }
        // update required interfaces table:
        //???: from class 5/5: ask component to give set of required interfaces
        // and iterate thru, for each interface in there, create a row in this table

        for (Class<?> intf : component.getRequiredInterfaces()) {
            requiredInterfaces.put(intf, component);
        }
        //find providers for the new component and hook him up:
        findProviders();
        // mvc stuff:
        changed();
    }

    public void remComponent(String name) throws Exception {
        Component component = components.get(name);
        if(component == null) throw new Exception("no component found");
        components.remove(name);
        // unhook removed guy from any clients:
        for(Class<?> intf: component.getProvidedInterfaces()) {  // need to go thru and see who needed the component and make required again if any
            for(Component client: components.values()) {
                if (client.getRequiredInterfaces().contains(intf)) {
                    client.setProvider(intf,  null);
                    requiredInterfaces.put(intf, client);
                }
            }
        }
        changed();
    }

    // each time we add a new component we try to connect as many clients and providers as we can:
    private void findProviders() throws Exception {
        Set<Class<?>> reqInterfaces = requiredInterfaces.keySet(); //.keySet() returns the interfaces from that map since those were the keys

        for(Class<?> intf: reqInterfaces) { //go thru all of them
            Component client = requiredInterfaces.get(intf); // who requires it
            Component provider = providedInterfaces.get(intf); // who provides it

            if (client != null && provider != null) {
                client.setProvider(intf,  provider); //establishes the connection btwn provider and require-er
                //requiredInterfaces.remove(intf); this line makes iterator obsolete
                // class 5-5: since found provider, can remove from required list... BUT this can mess things up, so you don't do this actually^
                requiredInterfaces.put(intf, null); // instead of changing the list while iterating, which can mess things up make null instead
            } //if provider not found, does not run
        }
    }

    public void launch(String name) throws Exception {
        try {
            // look up component and call main if it's an App
            Component c = components.get(name);
            if (c instanceof App) {
                ((App) c).main();
            }
        } catch(Exception e) {
            mvc.Utilities.error(e);
            e.printStackTrace();
        }
    }

    // needed by File/Open to restore component.fields
    public void initContainer(){
        for(Component c: components.values()) c.initComponent();
        changed(); // needed?
    }

}

