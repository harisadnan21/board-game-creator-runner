package oogasalad.engine.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * @author Jake Heller
 */
public class Cell extends Group {

  private Shape myShape;
  private Group myRoot;
  private ImageView myPiece;

  private double myWidth;
  private double myHeight;
  private double myX;
  private double myY;

  /**
   *
   * @param x top left x
   * @param y top left y
   * @param width cell width
   * @param height cell height
   */
  public Cell(double x, double y, double width, double height) {
    myWidth = width;
    myHeight = height;
    myX = x;
    myY = y;
    myRoot = new Group();
    myShape = new Rectangle(x, y, 0.95* width, 0.95* height);
    myShape.setFill(Color.GREEN);
    this.getChildren().add(myShape);
  }

  @Deprecated
  public Group getRoot() {
    return myRoot;
  }

  public void addPiece(String imagePath) {
    removePiece();
    Image image = new Image(imagePath);
    myPiece = new ImageView(image);
    myPiece.setFitWidth(myWidth);
    myPiece.setFitHeight(myHeight);
    myPiece.setX(myX);
    myPiece.setY(myY);
    this.getChildren().add(myPiece);

    System.out.println("Piece added");
  }

  public void removePiece() {
    if (myPiece != null) {
      this.getChildren().remove(myPiece);
    }
  }
}
