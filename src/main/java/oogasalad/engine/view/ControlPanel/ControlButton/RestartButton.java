package oogasalad.engine.view.ControlPanel.ControlButton;

public class RestartButton extends ControlButton {

  public RestartButton(String language, Runnable setRestart) {
    super(language);
    imagePath = IMAGES_FOLDER + imBundle.getString("Restart");
    createButton();
    setAction(setRestart);
  }
}
