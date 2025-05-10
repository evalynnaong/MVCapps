package midterm;


import static mvc.Utilities.rng;

public class RandomlyCheat implements Strategy{
    public boolean choose() {
        int r = rng.nextInt(2); // 0 or 1
        if (r == 0){
            return true;
        } else {
            return false;
        }
    }
}
