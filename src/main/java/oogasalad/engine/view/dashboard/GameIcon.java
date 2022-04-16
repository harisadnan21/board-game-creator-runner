package oogasalad.engine.view.dashboard;

import java.io.File;
import java.util.function.Consumer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class GameIcon extends VBox {
  private ImageView image;
  private String myName;
  private File myGameFolder;
  private Consumer<File> myStartGame;

  public GameIcon(File gameFolder, String imagePath, String name, Consumer<File> startGame) {
    image = new ImageView(new Image(imagePath));
    myName = name;
    myGameFolder = gameFolder;
    myStartGame = startGame;
    createIcon();
    addMouseClick();

  }

  private void createIcon() {
    this.getChildren().add(image);
    Text name = new Text(myName);
    name.getStyleClass().add("iconName");
    this.getChildren().add(new Text(myName));
  }

  private void addMouseClick(){
    this.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
      iconClicked();
    });
  }

  private void iconClicked(){
    myStartGame.accept(myGameFolder);
  }








}
