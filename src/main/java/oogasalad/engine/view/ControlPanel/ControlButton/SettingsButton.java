package oogasalad.engine.view.ControlPanel.ControlButton;

public class SettingsButton extends ControlButton {

  public SettingsButton(String language, Runnable setSettings) {
    super(language);
    imagePath = IMAGES_FOLDER + imBundle.getString("Gear");
    createButton();
    setAction(setSettings);
  }
}
