package oogasalad.engine.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Pair;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.Piece;
import oogasalad.engine.model.board.Position;

public class BoardView implements PropertyChangeListener{

  public static String IMAGES_FOLDER = "images/";
  public static String BLACK_KNIGHT = IMAGES_FOLDER + "black_knight.png";
  public static String WHITE_KNIGHT = IMAGES_FOLDER + "white_knight.png";
  public static double BOARD_OUTLINE_SIZE = 4;


  private Controller myController;

  private Cell[][] myGrid;

  private StackPane root;
  private GridPane gridRoot;
  private GameUpdateText text;

  public BoardView(int rows, int columns, double width, double height) {
    text = new GameUpdateText();
    root = new StackPane();
    gridRoot = new GridPane();
    myGrid = new Cell[rows][columns];

    Pair<Double, Double> cellSize = calcCellSize(rows, columns, width, height);
    double cellWidth = cellSize.getKey();
    double cellHeight = cellSize.getValue();

    makeBoardBacking(width, height, cellSize, rows, columns);
//    double x = 0;
//    double y = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Cell temp = new Cell(i, j, cellWidth, cellHeight);
        gridRoot.add(temp, i, j);
        myGrid[i][j] = temp;

        int finalI = i;
        int finalJ = j;

        myGrid[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
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
    updateBoard(nextState);
    text.updateText(i, j);
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
    for (Pair<Position, Piece> piece: board) {
      Position pos = piece.getKey();
      if (piece.getValue() != null) {
        myGrid[pos.getI()][pos.getJ()].addPiece(BLACK_KNIGHT);
      }
      else {
        myGrid[pos.getI()][pos.getJ()].removePiece();
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
