package midterm;

import mvc.Command;
import mvc.Model;

public class MTCommand3 extends Command {
    private MTModel mtmodel;

    public MTCommand3(Model model) {
        super(model);
        this.mtmodel = (MTModel) model;

    }

    @Override
    public void execute() throws Exception{
        if (mtmodel == null) {
            throw new Exception("model is missing field");
        }
        mtmodel.clear(); //some method from mTModel, the concrete model
    }
}
