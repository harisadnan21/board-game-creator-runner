package oogasalad.engine.view.ControlPanel;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class SettingsControlPanel extends ControlPanel {
  public static String INFO_IMAGE = IMAGES_FOLDER + imBundle.getString("Info");
  public static String SETTINGS_IMAGE = IMAGES_FOLDER + imBundle.getString("Gear");
  
  Button info;
  Button settings;

  public SettingsControlPanel() {
    super();
  }

  public Node getRoot() {
    return root;
  }

  protected void createButtons() {
    info = createButton(INFO_IMAGE);
    settings = createButton(SETTINGS_IMAGE);
    root.getChildren().addAll(info, settings);
  }
  public Button getInfoButton(){
    return info;
  }
  public Button getSettingsButton(){return settings;}

}
