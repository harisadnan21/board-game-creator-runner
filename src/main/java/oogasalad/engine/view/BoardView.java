package oogasalad.engine.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Pair;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;
import oogasalad.engine.model.engine.PieceSelectionEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import oogasalad.engine.model.setup.parsing.GameParser;

public class BoardView implements PropertyChangeListener{
  private static final Logger LOG = LogManager.getLogger(PieceSelectionEngine.class);
  public static String IMAGES_FOLDER = "images/";
  public static String BLACK_KNIGHT = IMAGES_FOLDER + "blueWhiteOrbit.png";
  public static String WHITE_KNIGHT = IMAGES_FOLDER + "purpleBlackOrbit.png";
  public static double BOARD_OUTLINE_SIZE = 4;

  public static Map<Integer, String> PIECE_TYPES = new HashMap<>();

  private Controller myController;

  private Cell[][] myGrid;

  private StackPane root;
  private GridPane gridRoot;
  private GameUpdateText text;

  public BoardView(int rows, int columns, double width, double height) {
    // TODO: extract this code to read data file
    PIECE_TYPES.put(0, WHITE_KNIGHT);
    PIECE_TYPES.put(1, BLACK_KNIGHT);

    text = new GameUpdateText();
    root = new StackPane();
    gridRoot = new GridPane();
    myGrid = new Cell[rows][columns];

    Pair<Double, Double> cellSize = calcCellSize(rows, columns, width, height);
    double cellWidth = cellSize.getKey();
    double cellHeight = cellSize.getValue();

    makeBoardBacking(width, height, cellSize, rows, columns);

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Cell temp = new Cell(i, j, cellWidth, cellHeight);
        gridRoot.add(temp.getMyRoot(), j, i); // documentation says the first input is column and the second is row
        myGrid[i][j] = temp;

        int finalI = i;
        int finalJ = j;

        myGrid[i][j].getMyRoot().addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
          try {
            cellClicked(e, finalI, finalJ);
          } catch (OutOfBoardException ex) {
            ex.printStackTrace();
          }
        });
      }
    }
    root.getChildren().add(gridRoot);
    gridRoot.setAlignment(Pos.CENTER);

    root.setAlignment(Pos.CENTER);
  }

  public void cellClicked(MouseEvent e, int i, int j) throws OutOfBoardException {
    Board nextState = myController.click(i, j);
    selectCell(i, j);
    updateBoard(nextState);
    text.updateText(nextState.getPlayer());
  }

  public void addController(Controller c) {
    myController = c;
  }

  public Text getText() {
    return text.getUpdateText();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Board board = (Board) evt.getSource();
    updateBoard(board);
  }

  private void makeBoardBacking(double width, double height, Pair<Double, Double> cellSize, int rows, int cols) {
    Rectangle foundation = new Rectangle(width, height, Color.web("#BEDDDB"));
    System.out.println(cellSize.toString());
    double cellW = cellSize.getKey();
    double cellH = cellSize.getValue();

    Rectangle outline = new Rectangle((cellW*cols)+BOARD_OUTLINE_SIZE, (cellH*rows)+BOARD_OUTLINE_SIZE, Color.web("#97CDC9"));
    root.getChildren().addAll(foundation, outline);
  }

  private Pair<Double, Double> calcCellSize(int rows, int cols, double width, double height) {
    double cellWidth = width / (rows + 1);
    double cellHeight = height / (cols + 1);

    return new Pair<>(cellWidth, cellHeight);
  }

  private void updateBoard(Board board) {
    setValidMarkers(board);
    for (PositionState piece: board) {
      Position pos = piece.getKey();
      if (piece.getValue() != null) {
        myGrid[pos.i()][pos.j()].addPiece(PIECE_TYPES.get(piece.getValue().getPieceRecord().player()));
      }
      else {
        myGrid[pos.i()][pos.j()].removePiece();
      }
    }
    checkForWin(board);
  }

  //checks to see if the winner variable in the returned new board has a valid winner value to end the game.
  private void checkForWin(Board board) {
    if(board.getWinner() != Board.NO_WINNER_YET){
      System.out.printf("gameOver! Player %d wins%n", board.getWinner());
      LOG.info("gameOver! Player {} wins%n", board.getWinner());
      try {
        displayGameOver(board);
        updateBoard(GameParser.getCheckersBoard());
      }
      catch(IOException e){
        e.printStackTrace();
        //TODO: Change
      }
    }
  }

  private void displayGameOver(Board board) {
    myController.resetGame();
  }

  /**
   * Adds a marker to all the cells that are valid moves for the currently selected piece
   * @param board - current Game Board
   */
  private void setValidMarkers(Board board) {
    if(board.getValidMoves()==null){
      clearValidMarks();
    }
    else{
      for(Position pos : board.getValidMoves()){
        myGrid[pos.i()][pos.j()].addValidMarker();
      }
    }
  }

  /**
   * Removes all the current valid moves markers from the scene. Called after selecting a move
   * or clicking off a selected piece
   */
  private void clearValidMarks(){
    for (Cell[] row : myGrid){
      for(Cell cell : row){
        cell.removeValidMarker();
      }
    }
  }

  private void selectCell(int i, int j) {
    clearCellSelection();
    Cell cell = myGrid[i][j];
    if (cell.containsPiece()) {
      myGrid[i][j].addSelectedHighlight();
    }
  }

  private void clearCellSelection() {
    for (Cell[] row : myGrid){
      for(Cell cell : row){
        cell.removeHighlight();
      }
    }
  }


  private Position getIndices(int index) {
    int i = index / myGrid.length;
    int j = index % myGrid[0].length;
    return new Position(i, j);
  }

  public Node getRoot() {
    return root;
  }
}
