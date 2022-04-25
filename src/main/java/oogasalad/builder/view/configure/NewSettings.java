package oogasalad.builder.view.configure;

import javafx.scene.control.ChoiceBox;

public abstract class NewSettings extends ChoiceBox<String> {
    protected String[] options;

    public NewSettings() {
        super();
        setup();
    }

    protected void setup() {
        this.getItems().addAll(options);
    }
}