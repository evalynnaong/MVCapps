/*

import mineField.Tile;
import mvc.Utilities;

public class MineDistributor extends Utilities {
    public static void place(Tile[][] field, int numMines) {
        int r = field.length;
        int c = field[0].length;
        int minesDistr = 0;

        while (minesDistr < numMines) {
            int x = rng.nextInt(20);
            int y = rng.nextInt(20);

            if (!field[x][y].getMineStatus() && !(x==0 && y==0)) {
                field[x][y].setMineStatus(true);
                minesDistr++;
            }
        }

    }
}

*/
