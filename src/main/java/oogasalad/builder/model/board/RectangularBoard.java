package oogasalad.builder.model.board;

import oogasalad.builder.model.exception.OccupiedCellException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Describes the behavior of a rectangular board.
 *
 * @author Shaan Gondalia
 */
public class RectangularBoard implements Board{

    private static final String EMPTY = "empty";
    private final String[][] cells;
    private final int width;
    private final int height;

    /**
     * Creates an empty board with the given dimensions
     *
     * @param height the number of rows in the board
     * @param width the number of cols in the board
     */
    public RectangularBoard(int width, int height) {
        this.width = width;
        this.height = height;
        cells = initializeCells(width, height);
    }

    /**
     * Attempts to place a piece at the given coordinates
     *
     * @param x the x location to place
     * @param y the y location to place
     * @param name the name of the piece to place
     * @throws OccupiedCellException if the requested indices are already occupied by a piece
     */
    public void placePiece(int x, int y, String name) throws OccupiedCellException {
        checkInBounds(x, y);
        checkEmpty(x, y);
        cells[x][y] = name;
    }

    /**
     * Finds the name of the piece at the given coordinates
     *
     * @param x the x location to query
     * @param y the y location to query
     * @return the name of the piece
     */
    public String findPieceAt(int x, int y) {
        checkInBounds(x, y);
        return cells[x][y];
    }

    /**
     * Clears the cell at the given coordinates
     * @param x the x location to clear
     * @param y the y location to clear
     */
    public void clearCell(int x, int y) {
        checkInBounds(x, y);
        cells[x][y] = EMPTY;
    }

    /**
     * Converts a Board into a String representing the board's JSON Format
     *
     * @return a String representation of the board's JSON Format
     */
    @Override
    public String toJSON(){
        JSONObject obj = new JSONObject();
        // TODO: Remove magic values
        obj.put("shape", "rectangle");
        obj.put("width", width);
        obj.put("height", height);
        obj.put("pieceConfiguration", pieceConfigToJSON());
        return obj.toString();
    }

    /**
     * Converts a JSON String into a Board
     *
     * @param json the JSON string
     * @return a board of type T made from the JSON string
     */
    @Override
    public Board fromJSON(String json){
        // TODO: Implement JSON parsing
        return null;
    }

    // Checks whether the requested indices are inbounds
    private void checkInBounds(int x, int y) {
        if (x < 0 || x > width || y < 0 || y > height) {
            throw new IndexOutOfBoundsException();
        }
    }

    // Checks whether the requested indices are empty
    private void checkEmpty(int x, int y) throws OccupiedCellException {
        if (!cells[x][y].equals(EMPTY)){
            throw new OccupiedCellException();
        }
    }

    // Initializes the cells to be empty
    private String[][] initializeCells(int width, int height) {
        String[][] cells = new String[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = EMPTY;
            }
        }
        return cells;
    }

    // Converts the piece configuration to a JSON array
    private JSONArray pieceConfigToJSON() {
        JSONArray config = new JSONArray();
        for (int i = 0; i < height; i++){
            JSONArray row = new JSONArray();
            for (int j =0; j<5; j++){
                row.put(cells[i][j]);
            }
            config.put(row);
        }
        return config;
    }
}