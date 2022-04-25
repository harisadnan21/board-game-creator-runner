package oogasalad.engine.view.Popup.SettingsView;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oogasalad.engine.view.OptionSelect.CSSSelect;
import oogasalad.engine.view.OptionSelect.MouseSoundSelect;
import oogasalad.engine.view.Popup.PopupView;
import oogasalad.engine.view.Popup.SettingsView.SettingsEntry.MouseSoundEntry;
import oogasalad.engine.view.Popup.SettingsView.SettingsEntry.ThemeSoundEntry;

/**
 * @author Cynthia France
 */
public class SettingsView extends PopupView {

  private VBox topLayout;
  private VBox layout;
  private Button returnToGame;
  private Text header;
  private CSSSelect cssDropdown;
  private MouseSoundEntry mouseSoundEntry;

  public SettingsView(String css, String language) {
    super(css, language);
    setup();
  }

  public Stage getStage() {
    return popupStage;
  }

  public Button getReturnToGame() {
    return returnToGame;
  }

  public CSSSelect getCssDropdown() {
    return cssDropdown;
  }

  public MouseSoundSelect getSoundDropdown() {return (MouseSoundSelect) mouseSoundEntry.getDropdown();}

  @Override
  protected void setup() {
    makeHeader();
    makeMenu();
  }

  private void makeMenu() {
    layout = new VBox();
    layout.setId("message-screen-layout");
    makeTheme();
    makeSound();
    makeButton();
    root.setCenter(layout);
  }

  private void makeSound() {
    MouseSoundSelect soundDropdown = new MouseSoundSelect();
    mouseSoundEntry = new MouseSoundEntry(myResources.getString("SoundSelect"), soundDropdown);
    layout.getChildren().add(mouseSoundEntry);
  }

  private void makeHeader() {
    topLayout = new SettingsHeader(myResources.getString("Settings")).getHeaderLayout();
    root.setTop(topLayout);
  }

  private void makeTheme() {
    cssDropdown = new CSSSelect();
    ThemeSoundEntry themeDropdown = new ThemeSoundEntry(myResources.getString("ThemeSelect"), cssDropdown);
    layout.getChildren().add(themeDropdown);
  }

  private void makeButton() {
    returnToGame = new Button(myResources.getString("Resume"));
    returnToGame.setId("message-screen-button");
    layout.getChildren().add(returnToGame);
  }
}
