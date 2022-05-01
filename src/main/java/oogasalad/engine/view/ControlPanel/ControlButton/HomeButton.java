package oogasalad.engine.view.ControlPanel.ControlButton;


import java.util.function.Consumer;
import java.util.function.Supplier;
import javafx.scene.Scene;

public class HomeButton extends ControlButton {

  public HomeButton(String language, Consumer<Scene> setHome, Supplier<Scene> getScene) {
    super(language);
    imagePath = IMAGES_FOLDER + imBundle.getString("Home");
    createButton();
    setAction(setHome, getScene);
  }

  protected void setAction(Consumer<Scene> setButton, Supplier<Scene> getScene) {
    this.setOnAction(e -> setButton.accept(getScene.get()));
  }
}
