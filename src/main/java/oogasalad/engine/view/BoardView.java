package oogasalad.engine.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;
import oogasalad.engine.controller.Controller;
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
  private Pair<Pair<Position, Piece>, String> change;
  private GameUpdateText text;

  public BoardView(int rows, int columns, double width, double height) {
    text = new GameUpdateText();
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
    myController.click(i, j);
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
    Board newBoard = (Board) evt.getSource();
    int index = 0;
    for (Pair<Position, Piece> piece: newBoard) {
      Position pos = piece.getKey();
      if (piece.getValue() != null) {
        myGrid[pos.getI()][pos.getJ()].addPiece(BLACK_KNIGHT);
      }
      else {
        myGrid[pos.getI()][pos.getJ()].removePiece();
      }
      index++;
    }
  }

  public Pair<Pair<Position, Piece>, String> getChange() {
    return change;
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
