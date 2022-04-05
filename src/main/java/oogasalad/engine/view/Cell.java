package oogasalad.engine.view;

import java.util.Objects;
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
public class Cell {

  public static int BUFFER = 2;
  public static String VALID_MARKER_PATH = BoardView.IMAGES_FOLDER + "valid_marker.png";

  private Shape myShape;
  private StackPane myRoot;
  private ImageView myPiece;
  private ImageView myValidMarker;

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
    myRoot.getStyleClass().add("cell");
    myShape = new Rectangle(width, height);
    setColor();

    myRoot.getChildren().add(myShape);
  }


  public Node getMyRoot() {
    return myRoot;
  }

  public void addPiece(String imagePath) {
    removePiece();
    myPiece = createImageView(imagePath, myWidth-BUFFER, myHeight-BUFFER);
    myRoot.getChildren().add(myPiece);
  }

  /**
   * Creates in Image View object given a path, width and height
   * @param imagePath - String of path to image being created
   * @param width - Double of desired width of image
   * @param height - Double of desired height of image
   * @return - ImageView created from image path
   */
  private ImageView createImageView(String imagePath, double width, double height) {
    ImageView myImageView = new ImageView( new Image(imagePath));
    myImageView.setFitWidth(width);
    myImageView.setFitHeight(height);
    return myImageView;

  }

  public void removePiece() {
    if (myPiece != null) {
      myRoot.getChildren().remove(myPiece);
    }
    myPiece = null;

  }

  /**
   * Adds marker to cell showing it is valid
   */
  public void addValidMarker(){
    myValidMarker = createImageView(VALID_MARKER_PATH, (myWidth-BUFFER)/3, (myHeight-BUFFER)/3);
    myRoot.getChildren().add(myValidMarker);
  }

  /**
   * Removes valid marker from cell if there is one present
   */
  public void removeValidMarker(){
    if(myValidMarker!= null) {
      myRoot.getChildren().remove(myValidMarker);
    }
    myValidMarker = null;

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
