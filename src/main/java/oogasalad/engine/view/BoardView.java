package oogasalad.engine.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import oogasalad.engine.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.Piece;
import oogasalad.engine.model.board.Position;

public class BoardView implements PropertyChangeListener{

  public static String IMAGES_FOLDER = "images/";
  public static String BLACK_KNIGHT = IMAGES_FOLDER + "black_knight.png";
  public static String WHITE_KNIGHT = IMAGES_FOLDER + "white_knight.png";


  private Controller myController;

  private Cell[][] myGrid;

  private Group myRoot;

  public BoardView(int rows, int columns, double width, double height) {

    myRoot = new Group();
    myGrid = new Cell[rows][columns];

    double cellWidth = width / columns;
    double cellHeight = height / rows;

    double x = 0;
    double y = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        myGrid[i][j] = new Cell(x, y, cellWidth, cellHeight);

        int finalI = i;
        int finalJ = j;

        myGrid[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {cellClicked(e, finalI, finalJ);});

        myRoot.getChildren().add(myGrid[i][j]);

        x += cellWidth;
      }
      x = 0;
      y += cellHeight;
    }
  }

  public void cellClicked(MouseEvent e, int i, int j) {
    Board nextState = myController.click(i, j);
    updateBoard(nextState);
  }

  public void addController(Controller c) {
    myController = c;
  }
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Board board = (Board) evt.getSource();
    updateBoard(board);
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

  public Group getRoot() {
    return myRoot;
  }
}
