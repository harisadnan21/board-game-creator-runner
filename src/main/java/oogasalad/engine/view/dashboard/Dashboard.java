package oogasalad.engine.view.dashboard;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

public class Dashboard extends BorderPane {
  private GameSelection myGames;
  public Dashboard(){
    myGames = new GameSelection();
    this.setCenter(myGames);
  }
}
