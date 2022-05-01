package oogasalad.engine.view.ControlPanel.ControlButton;

public class PauseButton extends ControlButton {

  public PauseButton(String language, Runnable setPause) {
    super(language);
    imagePath = IMAGES_FOLDER + imBundle.getString("Pause");
    createButton();
    setAction(setPause);
  }
}
