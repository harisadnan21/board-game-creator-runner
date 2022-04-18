package oogasalad.engine.view.dashboard;

import javafx.scene.layout.BorderPane;


public class Dashboard extends BorderPane {
  private final GameSelection myGames;
  private final InfoPanel myInfoTab;

  public Dashboard(){
    myInfoTab = new InfoPanel();
    myGames = new GameSelection();
    this.setTop(new Title());
    this.setCenter(myGames);
    this.setRight(new InfoPanel());

  }
}
