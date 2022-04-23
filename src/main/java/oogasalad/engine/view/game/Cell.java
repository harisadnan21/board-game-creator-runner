package oogasalad.engine.view.game;

import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Jake Heller, Cynthia France
 */
public class Cell {

  public static int BUFFER = 2;
  public static String VALID_MARKER_PATH = BoardView.IMAGES_FOLDER + "valid_marker.png";
  public static double OPACITY = 0.6;
  private static final Logger LOG = LogManager.getLogger(Cell.class);
  private Shape myShape;
  private StackPane myRoot;
  private ImageView myPiece;
  private ImageView myValidMarker;
  private Rectangle highlight;

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
  public Cell(int x, int y, double width, double height, Optional<String> hexColor) {
    myWidth = width;
    myHeight = height;
    myX = x;
    myY = y;
    myRoot = new StackPane();
    myRoot.getStyleClass().add("cell");
    myShape = new Rectangle(width, height);
    setColor(hexColor);

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
    LOG.debug(imagePath);
    ImageView myImageView = new ImageView(new Image(imagePath));
    //ImageView myImageView = new ImageView();
    myImageView.setId("valid-marker");
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

  public void addSelectedHighlight() {
    highlight = new Rectangle(myWidth-BUFFER, myHeight-BUFFER);
    highlight.setId("cell-highlight");

    myRoot.getChildren().add(highlight);
    System.out.println("selected");
  }

  public void removeHighlight() {
    myRoot.getChildren().remove(highlight);
    highlight = null;
  }

  public boolean containsPiece() {
    return myRoot.getChildren().contains(myPiece);
  }

  private void setColor(Optional<String> hexColor) {
    if (hexColor.isPresent()) {
      myShape.setFill(Color.web(hexColor.get()));
    }
    else {
      setDefaultColor();
    }
  }

  private void setDefaultColor() {
    if ((myX+myY)%2 == 0) {
      myShape.setId("cell-type-A");
    }
    else {
      myShape.setId("cell-type-B");
    }
  }
}
