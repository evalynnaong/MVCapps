package midterm;

import java.io.Serializable;

public class Prisoner implements Serializable {
    public int score;
    public Strategy strategy;
    public boolean play;
    public Prisoner() {
        score = 0; // initialize to 0
        strategy = new AlwaysCooperate(); // default to Always Cooperate
        play = strategy.choose();
    }

    /* cooperate = true, cheat = false*/
    public void playOnce(Prisoner p2){
        play = strategy.choose(); // make sure to update, in case of random play will change
        if (play && p2.getStrategy().choose()) { // both cooperate
            score += 3;
            p2.setScore(3);
        } else if (!play && p2.getStrategy().choose()) { //p1 cheat, p2 coop
            score += 5;
        } else if (play && !p2.getStrategy().choose()) { //p1 coop, p2 cheat
            p2.setScore(5);
        }

    }
    public void setStrategy(int strat) {
        if (strat == 0){
            strategy = new AlwaysCheat();
            play = strategy.choose();
        } else if (strat == 1) {
            strategy = new AlwaysCooperate();
            play = strategy.choose();
        } else {
            strategy = new RandomlyCheat();
            play = strategy.choose();
        }
    }
    public Strategy getStrategy(){return strategy;}
    public void setScore(int n) {score += n;} // really is "add score"
    public int getScore() {return score;}
}
