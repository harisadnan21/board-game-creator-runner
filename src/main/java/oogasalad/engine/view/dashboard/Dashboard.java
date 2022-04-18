package oogasalad.engine.view.dashboard;

import java.io.File;
import java.util.function.Consumer;
import javafx.scene.layout.BorderPane;


public class Dashboard extends BorderPane {
  private final GameSelection myGames;
  private final InfoPanel myInfoTab;

  public Dashboard(Consumer<File> startGame){
    myInfoTab = new InfoPanel(startGame);
    myGames = new GameSelection(myInfoTab::update);
    this.setTop(new Title());
    this.setCenter(myGames);
    this.setRight(myInfoTab);
  }
}
