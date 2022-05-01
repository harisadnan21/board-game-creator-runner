package oogasalad.engine.view.ControlPanel;

import javafx.scene.control.Button;
import oogasalad.engine.view.ControlPanel.ControlButton.InfoButton;
import oogasalad.engine.view.ControlPanel.ControlButton.SettingsButton;

/**
 *
 * Holds all application-related controls, including information and theme/sound setting selection
 * Inherits from ControlPanel
 *
 * @author Cynthia France
 */
public class SettingsControlPanel extends ControlPanel {
  private Button info;
  private Button settings;
  private Runnable setInfoButton;
  private Runnable setSettingsButton;

  /**
   *
   * creates a control panel that handles applciation-related requests
   *
   * @param language user-specified language in which the UI is displayed in
   */
  public SettingsControlPanel(String language, Runnable setInfo, Runnable setSettings) {
    super(language);
    setInfoButton = setInfo;
    setSettingsButton = setSettings;
    createButtons(language);
  }

  @Override
  protected void createButtons(String language) {
    info = new InfoButton(language, setInfoButton);
    settings = new SettingsButton(language, setSettingsButton);
    root.getChildren().addAll(info, settings);
  }

}
