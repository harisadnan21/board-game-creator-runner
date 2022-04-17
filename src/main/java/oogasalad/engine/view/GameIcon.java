package oogasalad.engine.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GameIcon extends VBox {
  private ImageView image;

  public GameIcon(String imagePath, String name) {
    image = new ImageView(new Image(imagePath));
  }
}
