package oogasalad.engine.view.dashboard;

import java.util.ResourceBundle;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InfoPanel extends VBox {
  public static final String DEFAULT = "/";
  private String title;
  private String author;
  private String description;
  private String language;
  private ResourceBundle INITIAL_TEXT;

  public InfoPanel(){
    language = "English";
    INITIAL_TEXT = ResourceBundle.getBundle(DEFAULT + "view/" +language);
    setInitialValues();
    displayText();
  }

  private void displayText() {
    this.getChildren().clear();
    Text infoTitle = new Text(title);
    infoTitle.getStyleClass().add("infoTitle");
    Text infoAuthor = new Text(author);
    infoTitle.getStyleClass().add("infoAuthor");
    Text infoDescription = new Text(description);
    infoTitle.getStyleClass().add("infoDescription");
    this.getChildren().addAll(infoTitle, infoAuthor, infoDescription);
  }

  private void setInitialValues() {
    title = INITIAL_TEXT.getString("initialInfoTitle");
    author = INITIAL_TEXT.getString("initialInfoAuthor");
    description = INITIAL_TEXT.getString("initialInfoDescription");
  }

}
