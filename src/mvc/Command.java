package mvc;

public abstract class Command extends Model{
    protected Model model;
    public Command(Model model) {
        this.model = model;
    }
    public abstract void execute() throws Exception; // should throw exception
    //public abstract void undo();
}
