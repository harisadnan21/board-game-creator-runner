package oogasalad.engine.view.setup.dashboard;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;
import javafx.scene.layout.FlowPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *Holds all game icons located in games data folder
 * @author Robert Cranston
 */
public class GameSelection extends FlowPane {
  private static final Logger LOG = LogManager.getLogger(GameSelection.class);
  public static final double GRID_WIDTH = 3;
  private double grid_length;
  public static final String DEFAULT_PATH = "default1.jpeg";

  private Map<String, String> availableGames;
  private File[] allGames;
  private final String GAME_PATH= "games";
  private final String IMG_FOLDER_PATH = "data/" + GAME_PATH;
  private final String LOGO_NAME = "logo";
  private final String RESOURCES_PATH = "/";
  private final String[] imageTypes= { ".jpeg", ".jpg", ".png" };

  public GameSelection(BiConsumer<Map<String, String>, File> updateInfo){
    File gameFolder = new File(IMG_FOLDER_PATH);
    allGames = gameFolder.listFiles(file -> !file.isHidden());
    this.getStyleClass().add("gameSelection");
    for(File game : allGames) {
      LOG.info("Game: {}", game.getName());
        displayGameIcon(updateInfo, game,
            getImagePath(new File(IMG_FOLDER_PATH + File.separator + game.getName()),
                game.getName()), game.getName());

    }
  }

  private void displayGameIcon(
      BiConsumer<Map<String, String>, File> updateInfo,
      File game, String imagePath, String name){
    this.getChildren().add(new GameIcon(updateInfo, game, imagePath, name));
  }

  private String getImagePath(File folder, String name){
    LOG.info("Folder {}\nName {}\n", folder, name);
    LOG.info("Files list size {}", folder.listFiles().length);
    Optional<String> file = Stream.of(Objects.requireNonNull(folder.listFiles()))
        .map(File::getName)
        .filter(fileName -> stringContainsAny(fileName, imageTypes))
        .findFirst();
    return file.map(s -> GAME_PATH + RESOURCES_PATH + folder.getName() + RESOURCES_PATH + s)
        .orElse(DEFAULT_PATH);

  }

  private boolean stringContainsAny(String input, String[] list){
    return Stream.of(list).anyMatch(input :: contains);
  }
}
