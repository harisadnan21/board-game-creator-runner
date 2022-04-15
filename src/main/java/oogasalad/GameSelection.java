package oogasalad;


import java.io.File;
import java.util.Map;
import java.util.Objects;
import javafx.scene.layout.Pane;

public class GameSelection extends Pane {
  private Map<String, String> availableGames;
  private final String GAME_PATHS = "data";

  public GameSelection(){
    File gameFolder = new File(GAME_PATHS);
    for (File file : Objects.requireNonNull(gameFolder.listFiles())) {
      if (file.isDirectory()) {
        System.out.println(file.getName());
      }
    }
  }


}
