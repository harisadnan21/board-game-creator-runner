package oogasalad.engine.view.setup.SelectionView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oogasalad.engine.model.ai.enums.Difficulty;
import org.apache.logging.log4j.util.TriConsumer;

/**
 *
 * Selection window for the users to choose between AI levels of difficulty
 * inherits from SelectionView
 *
 * @author Cynthia France, Robert Cranston
 */
public class AISelectView extends SelectionView {

  private TriConsumer<File, Stage, String[]> start;
  private File game;
  private Stage newStage;

  /**
   *
   * creates the window for users to select ai difficulty
   *
   * @param w width of window
   * @param h height of window
   * @param css the filepath for styling
   * @param language user-specified language in which the UI is displayed in
   * @param game game folder
   * @param newStage new stage to open the game
   * @param startGame tri-consumer to start game play
   */
  public AISelectView(double w, double h, String css, String language, File game, Stage newStage, TriConsumer<File, Stage, String[]> startGame) {
    super(w, h, css, language);
    start = startGame;
    this.game = game;
    this.newStage = newStage;
  }

  protected void setup() {
    windowLayout = new VBox();
    windowLayout.setId("ai-window-layout");
    makeText();
    makeButtons();
    windowLayout.getChildren().addAll(insns, buttonLayout);
  }

  protected void makeText() {
    insns = new Text(myResources.getString("AIInstructions"));
    insns.setId("ai-insns");
  }

  protected void makeButtons() {
    buttonLayout = new FlowPane();
    buttonLayout.setId("ai-button-layout");
    Difficulty[] aiDifficulties = Difficulty.class.getEnumConstants();
    for (Difficulty d : aiDifficulties) {
      Button b = makeButton(d.toString());
      b.setOnAction(e -> start.accept(game, newStage, new String[]{"singlePlayer", d.toString()}));
      buttonLayout.getChildren().add(b);
    }
    buttonLayout.setPrefWrapLength(500);
  }

  protected Button makeButton(String text) {
    Button b = new Button(text);
    b.setId("p-mode-button");
    return b;
  }
}
