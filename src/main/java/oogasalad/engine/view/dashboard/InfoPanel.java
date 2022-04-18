package oogasalad.engine.view.dashboard;

import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.jooq.lambda.function.Consumer0;

public class InfoPanel extends StackPane {
  public static final String DEFAULT = "/";
  public static final String RESOURCES = DEFAULT + "view/";
  private String title;
  private String author;
  private VBox infoHolder;
  private String description;
  private String language;
  private ResourceBundle initialText;

  public InfoPanel(Consumer0 startGame){
    this.getStyleClass().add("infoHolder");
    infoHolder = new VBox();
    infoHolder.getStyleClass().add("infoPanel");
    this.getChildren().add(infoHolder);
    language = "English";
    initialText = ResourceBundle.getBundle(RESOURCES +language);
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
    title = initialText.getString("initialInfoTitle");
    author = initialText.getString("initialInfoAuthor");
    description = initialText.getString("initialInfoDescription");
  }



}
