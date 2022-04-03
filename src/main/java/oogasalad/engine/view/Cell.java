package oogasalad.engine.view;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * @author Jake Heller
 */
public class Cell extends Group {

  public static int BUFFER = 2;

  private Shape myShape;
  private StackPane myRoot;
  private ImageView myPiece;

  private double myWidth;
  private double myHeight;
  private double myX;
  private double myY;

  /**
   *
   * @param x x index in board
   * @param y y index in board
   * @param width cell width
   * @param height cell height
   */
  public Cell(int x, int y, double width, double height) {
    myWidth = width;
    myHeight = height;
    myX = x;
    myY = y;
    myRoot = new StackPane();
    myRoot.setAlignment(Pos.CENTER);
    myShape = new Rectangle(width, height);
    setColor();
    this.getChildren().add(myShape);
  }

  @Deprecated
  public Node getRoot() {
    return myRoot;
  }

  public void addPiece(String imagePath) {
    removePiece();
    Image image = new Image(imagePath);
    myPiece = new ImageView(image);
    myPiece.setFitWidth(myWidth-BUFFER);
    myPiece.setFitHeight(myHeight-BUFFER);
    this.getChildren().add(myPiece);
  }

  public void removePiece() {
    if (myPiece != null) {
      this.getChildren().remove(myPiece);
    }
  }

  private void setColor() {
    if ((myX+myY)%2 == 0) {
      myShape.setFill(Color.web("#BEDDDB"));
    }
    else {
      myShape.setFill(Color.web("#97CDC9"));
    }
  }
}
