package midterm;

import mvc.Command;
import mvc.Model;

public class MTCommand2 extends Command {
    private MTModel mtmodel;
    private int n;

    public MTCommand2(Model model, int input) {
        super(model);
        this.mtmodel = (MTModel) model;
        this.n = input;

    }

    @Override
    public void execute() throws Exception{
        if (mtmodel == null) {
            throw new Exception("model is missing field");
        }
        mtmodel.mul(n); //some method from mTModel, the concrete model
    }
}
