package oogasalad.builder.view.tab;


import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import oogasalad.builder.view.ViewResourcesSingleton;

/**
 * The representation of the TitlePane which sets up the title used for all the tabs
 *
 * @author Mike Keohane
 */
public class TitlePane extends HBox {

  /**
   * Initializes a formatted title retrieved from properties file using the key
   *
   * @param titleKey - key to correlate with title to use
   */
  public TitlePane(String titleKey) {

    Label titleText = new Label(ViewResourcesSingleton.getInstance().getString(titleKey));
    titleText.setId("titleText");
    titleText.getStyleClass().add("titleText");

    this.getChildren().add(titleText);
    this.setId("titlePane");
    this.getStyleClass().add("titleBox");

  }
}
