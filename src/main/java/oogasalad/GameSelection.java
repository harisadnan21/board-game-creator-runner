package oogasalad;


import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameSelection extends Pane {
  private Map<String, String> availableGames;
  private File[] allGames;
  private final String IMG_FOLDER_PATH = "games/checkers";
  private final String GAME_PATHS = "data/games/checkers";
  private final String RESOURCES_PATH = "/";
  private final String[] imageTypes= { ".jpeg", ".jpg", ".png" };

  public GameSelection(){
    File gameFolder = new File(GAME_PATHS);
    allGames = gameFolder.listFiles();

    String path = getImagePath(gameFolder);
    displayGameIcon(path);

  }

  private void displayGameIcon(String imagePath){
    ImageView image = new ImageView(new Image(imagePath));
    this.getChildren().add(image);

  }

  private String getImagePath(File folder){
    Optional<String> file = Stream.of(Objects.requireNonNull(folder.listFiles()))
        .map(File::getName)
        .filter(name -> stringContainsAny(name, imageTypes))
        .findFirst();
    return IMG_FOLDER_PATH + RESOURCES_PATH + file.get();
  }

  private boolean stringContainsAny(String input, String[] list){
    return Stream.of(list).anyMatch(input :: contains);
  }
      
  


}
