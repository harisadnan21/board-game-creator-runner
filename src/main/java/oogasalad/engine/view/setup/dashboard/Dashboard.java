package oogasalad.engine.view.setup.dashboard;

import java.io.File;
import java.util.function.Consumer;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javax.swing.Scrollable;

/**
 * View that holds all dashboard elements
 * @author Robert Cranston
 */
public class Dashboard extends BorderPane {
  private final ScrollPane myGames;
  private final InfoPanel myInfoTab;

  /**
   * Initializes the dashboard
   * @param startGame Callback to start the selected game
   */
  public Dashboard(Consumer<File> startGame){
    myInfoTab = new InfoPanel(startGame);
    myGames = new ScrollPane(new GameSelection(myInfoTab::update));
    myGames.getStyleClass().add("scroll");
    this.setTop(new Title());
    this.setCenter(myGames);
    this.setRight(myInfoTab);
  }
}
