package oogasalad.engine.view.ControlPanel;

import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 *
 * Holds all application-related controls, including information and theme/sound setting selection
 * Inherits from ControlPanel
 *
 * @author Cynthia France
 */
public class SettingsControlPanel extends ControlPanel {
  public static String INFO_IMAGE = IMAGES_FOLDER + imBundle.getString("Info");
  public static String SETTINGS_IMAGE = IMAGES_FOLDER + imBundle.getString("Gear");
  
  Button info;
  Button settings;

  /**
   *
   * creates a control panel that handles applciation-related requests
   *
   * @param language user-specified language in which the UI is displayed in
   */
  public SettingsControlPanel(String language) {
    super(language);
  }

  /**
   *
   * returns the info button for GameView to use
   *
   * @return the info button in this panel
   */
  public Button getInfoButton(){
    return info;
  }

  /**
   *
   * returns the settings button for GameView to use
   *
   * @return the settings button in this panel
   */
  public Button getSettingsButton(){return settings;}

  protected void createButtons() {
    info = createButton(INFO_IMAGE);
    settings = createButton(SETTINGS_IMAGE);
    root.getChildren().addAll(info, settings);
  }

}
