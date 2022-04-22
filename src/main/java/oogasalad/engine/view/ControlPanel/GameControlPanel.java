package oogasalad.engine.view.ControlPanel;

import java.io.File;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.control.Button;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.driver.BoardHistoryException;

public class GameControlPanel extends ControlPanel {
  public static String HOME_IMAGE = IMAGES_FOLDER + imBundle.getString("Home");
  public static String RESTART_IMAGE = IMAGES_FOLDER + imBundle.getString("Restart");
  public static String BACK_IMAGE = IMAGES_FOLDER + imBundle.getString("Back");
  public static String PAUSE_IMAGE = IMAGES_FOLDER + imBundle.getString("Pause");

  private Controller myController;
  private Button home;
  private Button restart;
  private Button undo;
  private Button pause;
  private Consumer<Board> updateBoard;

  public GameControlPanel(Controller controller, Consumer<Board> updateBoard) {
    super();
    myController = controller;
    this.updateBoard = updateBoard;
  }

  public Node getRoot() {
    return root;
  }

  protected void createButtons() {
    home = createButton(HOME_IMAGE);
    home.setOnAction(e -> myController.startGame());
    restart = createButton(RESTART_IMAGE);
    restart.setOnAction(e -> restartGame());
    undo = createButton(BACK_IMAGE);
    undo.setOnAction(e -> undoMove());
    pause = createButton(PAUSE_IMAGE);
    root.getChildren().addAll(home, restart, undo, pause);
  }

  private void undoMove() {
    try {
      Board currBoard = myController.undoGameOnce();
      updateBoard(currBoard);
    } catch (BoardHistoryException ex) {
      ex.printStackTrace();
    }
  }

  private void restartGame() {
    Board currBoard = myController.resetGame();
    updateBoard(currBoard);
  }

  private void updateBoard(Board b) {
    updateBoard.accept(b);
  }

  public Button getPause() {
    return pause;
  }

  public Button getHome() {
    return home;
  }

  public Button getUndo(){return undo;}

  public Button getRestart(){return restart;}

}
