package oogasalad.engine.view;

import javafx.scene.control.ChoiceBox;

public class LanguageSelect extends ChoiceBox<String> {
  private String[] languages = {"English", "Spanish", "French", "German"};

  public LanguageSelect() {
    super();
    this.getItems().addAll(languages);
  }

  public String getLanguage() {
    if (this.getValue() == null) {
      return "English";
    }
    else {
      return this.getValue();
    }
  }

}
