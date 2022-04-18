package oogasalad.engine.view.dashboard;

import java.io.File;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InfoPanel extends StackPane {
  public static final String DEFAULT = "/";
  public static final String RESOURCES = DEFAULT + "view/";
  private static final Logger LOG = LogManager.getLogger(InfoPanel.class);
  private VBox infoHolder;
  private Label infoTitle;
  private Label infoAuthor;
  private Label infoDescription;
  private String language;
  private ResourceBundle initialText;
  private boolean displayButton;
  private File currentGame;
  private Consumer<File> startGame;


  public InfoPanel(Consumer<File> startGame){
    this.startGame = startGame;
    this.getStyleClass().add("infoHolder");
    infoHolder = new VBox();
    infoHolder.getStyleClass().add("infoPanel");
    this.getChildren().add(infoHolder);
    language = "English";
    initialText = ResourceBundle.getBundle(RESOURCES +language);
    displayText();
    setInitialValues();
  }

  private void displayText() {
    infoTitle = new Label();
    infoTitle.getStyleClass().add("infoTitle");
    infoAuthor = new Label();
    infoAuthor.getStyleClass().add("infoAuthor");
    infoDescription = new Label();
    infoDescription.getStyleClass().add("infoDescription");
    infoHolder.getChildren().addAll(infoTitle, infoAuthor, infoDescription);
  }

  private void setInitialValues() {
    infoTitle.setText(initialText.getString("initialInfoTitle"));
    infoAuthor.setText(initialText.getString("initialInfoAuthor"));
    infoDescription.setText(initialText.getString("initialInfoDescription"));
  }

  public void update(Map<String, String> data, File gameFolder) {
    LOG.info("new data {}", data);
    infoTitle.setText(data.get("name"));
    infoAuthor.setText(data.get("author"));
    infoDescription.setText(data.get("description"));
    currentGame = gameFolder;
    if(!displayButton) {
      displayPlay();
    }
  }

  private void displayPlay() {
    displayButton = true;
    Button play = new Button("Play Game");
    play.setOnAction( game-> startGame.accept(currentGame));
    infoHolder.getChildren().add(play);
  }
}
