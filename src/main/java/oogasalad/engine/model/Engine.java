package oogasalad.engine.model;

import oogasalad.engine.model.action.Action;
import oogasalad.engine.model.action.Place;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.conditions.Condition;
import oogasalad.engine.model.conditions.IsEmpty;
import oogasalad.engine.model.move.Movement;

public class Engine {

  private Game myGame;

  private Movement myMove;

  public Engine(Game game) {
    myGame = game;
    createTicTacToeMove();
  }

  public Board selectCell(int x, int y) throws OutOfBoardException {
    Board board = myGame.getBoard();

//    System.out.printf("Cell %d, %d\n", x, y);
//    System.out.printf("Move is %b\n", myMove.isValid(board, x, y));
    if (myMove.isValid(board, x, y)) {
      board = myMove.doMovement(board, x, y);
      myGame.setBoard(board);
    }
    return board;
  }

  private void createTicTacToeMove() {
    // should Conditions and Actions have the relative relationships build into them?
    Condition[] conditions = new Condition[]{new IsEmpty(new int[]{0, 0})};
    Action[] actions = new Action[]{new Place(new int[]{0, 0, 0, 0})};

    myMove = new Movement(conditions, actions);
  }
}
