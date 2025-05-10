package midterm;

import mvc.Model;

import java.util.ArrayList;

public class Tournament extends Model {
    public static int size = 10; // size of tournament
    ArrayList<Prisoner> tourney = new ArrayList<>(size); // array of players

    public Tournament() {
        for(int i = 0; i < size; i++) { // create tournament of size = 10 prisoners
            tourney.add(new Prisoner());
            int strat = rng.nextInt(3); // randomly assign a strategy
            tourney.get(i).setStrategy(strat);
        }
    }

    public void play() {
        for(int i = 0; i < size/2; i++) { // randomly match players and play them
            int rand = rng.nextInt(size/2, size);
            tourney.get(i).playOnce(tourney.get(rand));

            // to test:
            System.out.println("Player " + i + " score: " + tourney.get(i).getScore());
            System.out.println("Player " + rand + " score: " + tourney.get(rand).getScore());

        }
        changed();
    }

    public static void main(String[] args) {
        Tournament test = new Tournament();
        test.play();
    }
}
