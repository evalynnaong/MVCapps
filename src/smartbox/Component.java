package smartbox;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class Component implements Serializable {

    private Set<Class<?>> requiredInterfaces;
    private Set<Class<?>> providedInterfaces;
    private transient Map<Class<?>, Field> fields; // transient because Field not serializable
    protected Container container;
    protected String name;

    public Component() {
        fields = new HashMap<Class<?>, Field>();
        // do not use list, since no sensible ordering of interfaces
        providedInterfaces = new HashSet<Class<?>>();
        requiredInterfaces = new HashSet<Class<?>>();
        computeRequiredInterfaces();
        computeProvidedInterfaces();
        container = null; // when get added to container, then this field will get set
        name = this.getClass().getSimpleName(); // returns name of class w/o package
    }

    // add needed getters & setters:

    public void setContainer(Container newContainer) {
        this.container = newContainer;
    }

    public Set<Class<?>> getProvidedInterfaces() {
        return providedInterfaces;
    }

    public Set<Class<?>> getRequiredInterfaces() {
        return requiredInterfaces;
    }

    public String toString() { return name; }

    // initializes fields and requiredInterfaces
    public void computeRequiredInterfaces() { //**will have declared field and that type of interface is required**
        Field[] fieldArray = this.getClass().getDeclaredFields();
        for(int i = 0; i < fieldArray.length; i++) {
            //if the type of field[i] is an interface, then add it to fields and requiredInterfaces ???
            //"if component of class requires interface, in other words has field of type interface"
            Class<?> type = fieldArray[i].getType();
            if(type.isInterface()) {
                requiredInterfaces.add(type);
            }
        }
    }

    // initializes provided interfaces
    public void computeProvidedInterfaces() { //** use reflection to figure out which interfaces required**
        // get interfaces implemented by the class of this component and add them to providedInterfaces
        Class<?> [] interfaces= this.getClass().getInterfaces();
        for(Class<?> intf : interfaces) {
            providedInterfaces.add(intf);
        }
    }

    // set the field of this object to the provider
    public void setProvider(Class<?> intf, Component provider) throws Exception {
    /*    Field field = fields.get(intf);
        //field.setAccessible(true);
        field.set(this, provider); // field probably needs to be public for this.
    */
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (intf.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                field.set(this, provider);
                return;
            }
        }

        throw new Exception("No field found in " + this.getClass().getName() +
                " that can accept provider for interface " + intf.getName());
    }

    // needed by file/open
    public void initComponent() {
        fields = new HashMap<Class<?>, Field>(); // since field not serializable, need to compute interfaces again
        computeProvidedInterfaces();
        computeRequiredInterfaces();
    }



}
