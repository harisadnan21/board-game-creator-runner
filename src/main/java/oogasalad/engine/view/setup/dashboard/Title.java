package oogasalad.engine.view.setup.dashboard;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class Title extends StackPane {
  public static final String GAME_TITLE = "OOGABOOGA";

  public Title(){

    this.getStyleClass().add("title");
    this.getChildren().add(new Text(GAME_TITLE));
  }
}
