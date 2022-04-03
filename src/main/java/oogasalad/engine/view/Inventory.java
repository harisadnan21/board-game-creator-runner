package oogasalad.engine.view;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

public class Inventory {

  public static String IMAGES_FOLDER = "images/";
  public static String BLACK_KNIGHT = IMAGES_FOLDER + "black_knight.png";

  private FlowPane root;
  private Text descriptor;

  public Inventory() {
    root = new FlowPane();
    root.setPadding(new Insets(5, 0, 5, 0));
    root.setVgap(4);
    root.setHgap(4);
    root.setPrefWrapLength(50);
    //root.setStyle("-fx-background-color: DAE6F3;");

    descriptor = new Text("Piece Inventory");
    root.getChildren().addAll(descriptor, new ExtraPiece(BLACK_KNIGHT).getRoot());
  }

  public FlowPane getRoot() {
    return root;
  }
}
