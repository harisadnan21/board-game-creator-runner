package oogasalad.engine.view;


import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oogasalad.engine.view.GameIcon;

public class GameSelection extends HBox {
  private Map<String, String> availableGames;
  private File[] allGames;
  private final String GAME_PATHS= "games";
  private final String IMG_FOLDER_PATH = "data/" + GAME_PATHS;
  private final String RESOURCES_PATH = "/";
  private final String[] imageTypes= { ".jpeg", ".jpg", ".png" };

  public GameSelection(){
    File gameFolder = new File(IMG_FOLDER_PATH);
    allGames = gameFolder.listFiles();

    for(File game : allGames) {
      displayGameIcon(getImagePath(game), game.getName());
    }

  }

  private void displayGameIcon(String imagePath, String name){
    this.getChildren().add(new GameIcon(imagePath, name));

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
