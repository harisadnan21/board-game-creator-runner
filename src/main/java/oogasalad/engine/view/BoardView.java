package oogasalad.engine.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventListener;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import oogasalad.engine.Controller;
import oogasalad.engine.model.Board;
import oogasalad.engine.model.Piece;
import oogasalad.engine.model.Position;

public class BoardView implements PropertyChangeListener{

  public static String IMAGES_FOLDER = "images/";
  public static String BLACK_KNIGHT = IMAGES_FOLDER + "black_knight.png";
  public static String WHITE_KNIGHT = IMAGES_FOLDER + "white_knight.png";


  private Controller myController;

  private Pane[][] myGrid;

  private Group myRoot;

  public BoardView(int rows, int columns, double width, double height) {

    myRoot = new Group();
    myGrid = new Pane[rows][columns];

    double cellWidth = width / columns;
    double cellHeight = height / rows;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        myGrid[i][j] = new StackPane();
        Rectangle rect = new Rectangle(cellWidth, cellHeight, Color.GREEN);
        Image horseIMG = new Image(BLACK_KNIGHT);
        ImageView piece = new ImageView(horseIMG);
        piece.setFitHeight(cellHeight);
        piece.setFitWidth(cellWidth);
        myGrid[i][j].getChildren().addAll(rect, piece);

        //rect.applyCss();
        //myGrid[i][j];

        int finalI = i;
        int finalJ = j;
        myGrid[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {cellClicked(e, finalI, finalJ);});

        myRoot.getChildren().add(myGrid[i][j]);
      }
    }
  }

  public void cellClicked(MouseEvent e, int i, int j) {
    myController.click(i, j);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Board newBoard = (Board) evt.getNewValue();
    int index = 0;
    for (Piece piece: newBoard) {
      if (piece != null) {
        Position pos = getIndices(index);
        //myGrid[pos.getI()][pos.getJ()].getChildren().add(horse);
      }
      index++;
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
