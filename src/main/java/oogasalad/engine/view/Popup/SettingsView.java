package oogasalad.engine.view.Popup;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import oogasalad.engine.view.OptionSelect.CSSSelect;
import oogasalad.engine.view.OptionSelect.MouseSoundSelect;
import oogasalad.engine.view.Popup.SettingsEntry.MouseSoundEntry;
import oogasalad.engine.view.Popup.SettingsEntry.ThemeSoundEntry;

public class SettingsView extends PopupView {

  private VBox topLayout;
  private VBox layout;
  private Button returnToGame;
  private HBox themeMenu;
  private Text header;
  private Text theme;
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
    layout = new VBox();
    layout.setId("message-screen-layout");
    makeHeader();
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
    topLayout = new VBox();
    topLayout.setId("header-layout");
    header = new Text(myResources.getString("Settings"));
    header.setId("settings-header");
    topLayout.getChildren().add(header);
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
