package midterm;

import mvc.Model;

//specific business logic for midterm app
public class MTModel extends Model {
    private int accumulator;
    public MTModel() {
        accumulator = 0; // initialize to 0
        //some business logic
    }
    public void add(int n) { // command 1
        accumulator += n;
        changed();
        //System.out.print(accumulator);
    }
    public void mul(int n) { // command 2
        accumulator = accumulator * n;
        changed();
    }

    public void clear() { // command 3
        accumulator = 0;
        changed();
    }

    public int getAccumulator() {return accumulator;}
}
