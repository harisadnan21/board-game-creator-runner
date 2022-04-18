package oogasalad.engine.view.dashboard;

import javafx.scene.layout.BorderPane;
import org.jooq.lambda.function.Consumer0;


public class Dashboard extends BorderPane {
  private final GameSelection myGames;
  private final InfoPanel myInfoTab;

  public Dashboard(Consumer0 startGame){
    myInfoTab = new InfoPanel(startGame);
    myGames = new GameSelection();
    this.setTop(new Title());
    this.setCenter(myGames);
    this.setRight(new InfoPanel(startGame));
  }
}
