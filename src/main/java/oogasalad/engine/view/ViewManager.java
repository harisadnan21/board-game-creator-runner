package oogasalad.engine.view;

import oogasalad.engine.controller.Controller;

public class ViewManager {
  public static double WIDTH = 600;
  public static double HEIGHT = 400;

  public ViewManager() {
  }

  public OpeningView createOpeningView() {
    OpeningView openingView = new OpeningView(WIDTH, HEIGHT);
    return openingView;
  }

  public GameView createGameView(BoardView board, Controller controller) {
    GameView gameView = new GameView(board, controller, WIDTH, HEIGHT);
    return gameView;
  }

}
