package oogasalad.engine.view.ControlPanel.ControlButton;

public class InfoButton extends ControlButton {

  public InfoButton(String language, Runnable setInfo) {
    super(language);
    imagePath = IMAGES_FOLDER + imBundle.getString("Info");
    createButton();
    setAction(setInfo);
  }
}
