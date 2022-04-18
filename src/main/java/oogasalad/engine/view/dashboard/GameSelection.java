package oogasalad.engine.view.dashboard;


import static java.lang.Math.ceil;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import javafx.scene.layout.FlowPane;


public class GameSelection extends FlowPane {
  public static final double GRID_WIDTH = 3;
  private double grid_length;

  private Map<String, String> availableGames;
  private File[] allGames;
  private final String GAME_PATH= "games";
  private final String IMG_FOLDER_PATH = "data/" + GAME_PATH;
  private final String RESOURCES_PATH = "/";
  private final String[] imageTypes= { ".jpeg", ".jpg", ".png" };

  public GameSelection(){
    File gameFolder = new File(IMG_FOLDER_PATH);
    allGames = gameFolder.listFiles();
    this.getStyleClass().add("gameSelection");
    for(File game : allGames) {
      displayGameIcon(game, getImagePath(new File(IMG_FOLDER_PATH+RESOURCES_PATH+"checkers/"), "checkers"), "checkers");
    }

  }



  private void displayGameIcon(File game, String imagePath, String name){
    this.getChildren().add(new GameIcon(game, imagePath, name, this::startGame ));
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
  private void startGame(File folder){
    System.out.println(folder.getName());
  }
      
  


}
