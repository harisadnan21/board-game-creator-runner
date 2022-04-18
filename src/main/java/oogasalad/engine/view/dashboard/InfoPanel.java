package oogasalad.engine.view.dashboard;

import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InfoPanel extends StackPane {
  public static final String DEFAULT = "/";
  private String title;
  private String author;
  private VBox infoHolder;
  private String description;
  private String language;
  private ResourceBundle INITIAL_TEXT;

  public InfoPanel(){
    this.getStyleClass().add("infoHolder");
    infoHolder = new VBox();
    infoHolder.getStyleClass().add("infoPanel");
    this.getChildren().add(infoHolder);
    language = "English";
    INITIAL_TEXT = ResourceBundle.getBundle(DEFAULT + "view/" +language);
    setInitialValues();
    displayText();
  }

  private void displayText() {
    infoHolder.getChildren().clear();
    Label infoTitle = new Label(title);
    infoTitle.getStyleClass().add("infoTitle");
    Label infoAuthor = new Label("Author: " + author);
    infoAuthor.getStyleClass().add("infoAuthor");
    Label infoDescription = new Label(description);
    infoDescription.getStyleClass().add("infoDescription");
    infoHolder.getChildren().addAll(infoTitle, infoAuthor, infoDescription);
  }

  private void setInitialValues() {
    title = INITIAL_TEXT.getString("initialInfoTitle");
    author = INITIAL_TEXT.getString("initialInfoAuthor");
    description = INITIAL_TEXT.getString("initialInfoDescription");
  }

}
