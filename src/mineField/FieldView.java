package mineField;

import mvc.Model;
import mvc.View;

import javax.swing.*;
import java.awt.*;

public class FieldView extends View {
    private static final int SIZE = 20;
    private JLabel[][] tiles = new JLabel[SIZE][SIZE];
    private Field field;

    public FieldView(Model m) {
        super(m);
        this.field = (Field) m;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 500));

        // Create minefield grid panel
        JPanel minefieldPanel = new JPanel(new GridLayout(SIZE, SIZE));
        for (int i = 0; i < SIZE; i++) { //row
            for (int j = 0; j < SIZE; j++) { //col
                tiles[i][j] = new JLabel("?", SwingConstants.CENTER);
                tiles[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tiles[i][j].setOpaque(true);
                tiles[i][j].setBackground(Color.DARK_GRAY);
                minefieldPanel.add(tiles[i][j]);
            }
        }
        int x = this.field.getPlayerX();
        int y = this.field.getPlayerY();
        int numMines = ((Field) field).countMines(x, y);
        tiles[y][x].setText(String.valueOf(numMines));
        tiles[y][x].setBorder(BorderFactory.createLineBorder(Color.white));
        tiles[y][x].setBackground(Color.white);
        add(minefieldPanel, BorderLayout.CENTER);
    }

    @Override
    public void update(String message) {
        System.out.println("update being caught");
        refresh2();
    }

    public void refresh2() {
        //get tile where player currently located, countMines and reset text
        int x = field.getPlayerX();
        int y = field.getPlayerY();

        System.out.println("x: " + x + " y: " +y);

        if(x == SIZE-1 && y == SIZE-1) {
            tiles[y][x].setText(":)");
            tiles[y][x].setBackground(Color.GREEN);
        }
        else {
            int numMines = this.field.getTile(x, y).getNumMines();
            System.out.println(numMines);
            tiles[y][x].setText(String.valueOf(numMines));
            tiles[y][x].setBorder(BorderFactory.createLineBorder(Color.white));
            tiles[y][x].setBackground(Color.WHITE);

            Tile tile = this.field.getTile(x, y);
            if (tile != null && tile.getMineStatus()) {
                tiles[y][x].setBackground(Color.RED);
            }
        }
    }

    @Override
    public void setModel(Model newModel) {
        super.setModel(newModel);

        System.out.println(field.getPlayerX() + " " + field.getPlayerY());

        initView(newModel);
        //repaint();
        refresh2();
        System.out.println(field.getPlayerX() + " " + field.getPlayerY());

    }

    public void initView(Model newModel) {
        this.field = (Field) newModel;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.field.getTile(i,j).getStepStatus()) {
                    int numMines = ((Field) field).countMines(i, j);
                    tiles[j][i].setText(String.valueOf(numMines)); //j, i
                    tiles[j][i].setBorder(BorderFactory.createLineBorder(Color.white));
                    tiles[j][i].setBackground(Color.white);

                } else {
                    tiles[j][i].setText("?");
                    tiles[j][i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    tiles[j][i].setOpaque(true);
                    tiles[j][i].setBackground(Color.DARK_GRAY);
                }
            }
        }

        int x = this.field.getPlayerX();
        int y = this.field.getPlayerY();
        int numMines = ((Field) field).countMines(x, y);
        tiles[y][x].setText(String.valueOf(numMines));
        tiles[y][x].setBorder(BorderFactory.createLineBorder(Color.white));
        tiles[y][x].setBackground(Color.white);
    }
}
