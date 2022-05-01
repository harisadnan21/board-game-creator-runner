package oogasalad.engine.view.ControlPanel.ControlButton;

public class UndoButton extends ControlButton {

  public UndoButton(String language, Runnable setUndo) {
    super(language);
    imagePath = IMAGES_FOLDER + imBundle.getString("Back");
    createButton();
    setAction(setUndo);
  }
}
