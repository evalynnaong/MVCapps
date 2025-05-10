package mineField;


import mvc.Model;

public class Field extends Model{
    private static final int size = 20;
    private Tile[][] field = new Tile[size][size];
    private int playerX;
    private int playerY; // Player starts at (0,0)
    public static int percentMined = 5; // % of tiles mined
    private boolean gameOver = false; // Track game state

    public Field() {
        int totalMines = (size * size * percentMined) / 100; // Determine number of mines

        initPlayer();

        // Initialize field with empty tiles
        for (int i = 0; i < size; i++) { //row
            for (int j = 0; j < size; j++) { //col
                field[i][j] = new Tile(i, j, false, false, 0);
            }
        }

        // step status of first step is true
        field[0][0].setStepStatus(true);

        // Randomly place mines
        for (int i = 0; i < totalMines; i++) {
            int x, y;
            do {
                x = rng.nextInt(size);
                y = rng.nextInt(size);
            } while (field[y][x].getMineStatus()); // Ensure we don't place a mine on an already mined tile

            field[y][x].setMineStatus(true);
        }

        // Count number of mines surrounding space
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int n = countMines(x, y);
                field[y][x].setNumMines(n);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            return field[y][x]; //field[row][col]
        }
        return null; // Handle out-of-bounds access safely
    }

    public void movePlayer(String direction) throws Exception{ // doesn't need to return anything...
        if (gameOver) {
            throw new Exception("Game over! Start new.");
        }

        int newX = playerX, newY = playerY;
        switch (direction) {
            case "NW": newX--; newY--; break;
            case "N":  newY--; break;
            case "NE": newX++; newY--; break;
            case "W":  newX--; break;
            case "E":  newX++; break;
            case "SW": newX--; newY++; break;
            case "S":  newY++; break;
            case "SE": newX++; newY++; break;
        }

        // Validate new position
        if (newX == size-1 && newY == size-1){
            playerX = newX;
            playerY = newY;

            field[playerY][playerX].setStepStatus(true);
            gameOver = true;
            changed();
            throw new Exception("Winner!");
        }
        else if (newX >= 0 && newX < size && newY >= 0 && newY < size) {
            playerX = newX;
            playerY = newY;

            field[playerY][playerX].setStepStatus(true);
            changed();

            // Check for mine
            if (field[playerY][playerX].getMineStatus()) {
                gameOver = true;
                throw new Exception("Game over");
            }
        } else {
            throw new Exception("Out of bounds");
        }
    }

    public void initPlayer() {
        this.playerX = 0;
        this.playerY = 0;
    }
    public int getPlayerX() { return playerX; }
    public int getPlayerY() { return playerY; }
    public int getSize() { return size; }
    public int countMines(int x, int y){
        int count = 0;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for(int i = 0; i < 8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < getSize() && ny < getSize()){
                if (getTile(nx, ny).getMineStatus()) {
                    count++;
                }
            }
        }
        return count;
    }
}
