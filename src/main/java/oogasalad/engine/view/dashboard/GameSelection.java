package oogasalad.engine.view.dashboard;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javafx.scene.layout.FlowPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GameSelection extends FlowPane {
  private static final Logger LOG = LogManager.getLogger(GameSelection.class);
  public static final double GRID_WIDTH = 3;
  private double grid_length;

  private Map<String, String> availableGames;
  private File[] allGames;
  private final String GAME_PATH= "games";
  private final String IMG_FOLDER_PATH = "data/" + GAME_PATH;
  private final String RESOURCES_PATH = "/";
  private final String[] imageTypes= { ".jpeg", ".jpg", ".png" };

  public GameSelection(BiConsumer<Map<String, String>, File> updateInfo){
    File gameFolder = new File(IMG_FOLDER_PATH);
    allGames = gameFolder.listFiles();
    this.getStyleClass().add("gameSelection");
    for(File game : allGames) {
      displayGameIcon(updateInfo, game, getImagePath(new File(IMG_FOLDER_PATH+RESOURCES_PATH+game.getName()+RESOURCES_PATH), game.getName()), game.getName());
    }
  }

  private void displayGameIcon(
      BiConsumer<Map<String, String>, File> updateInfo,
      File game, String imagePath, String name){
    this.getChildren().add(new GameIcon(updateInfo, game, imagePath, name));
  }

  private String getImagePath(File folder, String name){
    Optional<String> file = Stream.of(Objects.requireNonNull(folder.listFiles()))
        .map(File::getName)
        .filter(fileName -> stringContainsAny(fileName, imageTypes))
        .findFirst();
    return GAME_PATH + RESOURCES_PATH + name + RESOURCES_PATH + file.get();
  }

  private boolean stringContainsAny(String input, String[] list){
    return Stream.of(list).anyMatch(input :: contains);
  }



      
  


}
