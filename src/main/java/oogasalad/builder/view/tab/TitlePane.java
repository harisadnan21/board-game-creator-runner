package oogasalad.builder.view.tab;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TitlePane {
    private HBox titleBox;

    public TitlePane(String titleKey, ResourceBundle resources) {

      titleBox = new HBox();

      Label titleText = new Label(resources.getString(titleKey));
      titleText.setId("titleText");

      titleBox.getChildren().add(titleText);
      titleBox.setId("titleBox");

    }

    public Node toNode(){
      return titleBox;
    }

}
