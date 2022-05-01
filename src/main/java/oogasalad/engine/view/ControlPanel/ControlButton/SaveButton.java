package oogasalad.engine.view.ControlPanel.ControlButton;

public class SaveButton extends ControlButton {

  public SaveButton(String language, Runnable setSave) {
    super(language);
    imagePath = IMAGES_FOLDER + imBundle.getString("Save");
    createButton();
    setAction(setSave);
  }
}
