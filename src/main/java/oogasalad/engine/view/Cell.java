package oogasalad.engine.view;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * @author Jake Heller
 */
public class Cell extends Group {

  private Shape myShape;
  private StackPane myRoot;
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
//  public Cell(double x, double y, double width, double height) {
//    myWidth = width;
//    myHeight = height;
//    myX = x;
//    myY = y;
//    myRoot = new Group();
//    myShape = new Rectangle(x, y, 0.95* width, 0.95* height);
//    myShape.setFill(Color.GREEN);
//    this.getChildren().add(myShape);
//  }

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
    myPiece.setFitWidth(myWidth-2);
    myPiece.setFitHeight(myHeight-2);
//    myPiece.setX(myX);
//    myPiece.setY(myY);
    this.getChildren().add(myPiece);

    // System.out.println("Piece added");
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
