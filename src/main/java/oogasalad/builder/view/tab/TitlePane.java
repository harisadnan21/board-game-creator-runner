package oogasalad.builder.view.tab;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import oogasalad.builder.view.ViewResourcesSingleton;

public class TitlePane {
    private HBox titleBox;

    public TitlePane(String titleKey) {

      titleBox = new HBox();

      Label titleText = new Label(ViewResourcesSingleton.getInstance().getString(titleKey));
      titleText.setId("titleText");

      titleBox.getChildren().add(titleText);
      titleBox.setId("titleBox");

    }

    public Node toNode(){
      return titleBox;
    }

}
