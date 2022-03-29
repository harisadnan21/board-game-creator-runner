package oogasalad.builder.model;

/**
 * The Board keeps track of the names and placement of pieces in the board configuration
 *
 * @author Shaan Gondalia
 */
public class Board {

    private String[][] cells;
    private final int width;
    private final int height;
    /**
     * Creates an empty board with the given dimensions
     *
     * @param height the number of rows in the board
     * @param width the number of cols in the board
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new String[width][height];
    }

    /**
     * @param x 
     * @param y 
     * @param name
     */
    public void placePiece(int x, int y, String name) throws Exception {
        checkInBounds(x, y);
        checkEmpty(x, y);
        cells[x][y] = name;
    }

    /**
     * Finds the name of the piece at the given coordinates
     *
     * @param x the x location of query
     * @param y the y location to query
     * @return the name of the piece
     */
    public String findPieceAt(int x, int y) {
        checkInBounds(x, y);
        return cells[x][y];
    }

    // Checks whether the requested indices are inbounds
    private void checkInBounds(int x, int y) {
        if (x < 0 || x > width || y < 0 || y > height) {
            throw new IndexOutOfBoundsException();
        }
    }

    // Checks whether the requested indices are empty
    private void checkEmpty(int x, int y) throws Exception {
        if (cells[x][y].equals("empty")){
            throw new Exception(); // TODO: Replace this with a custom exception
        }
    }


}