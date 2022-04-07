package oogasalad.engine.model.engine;

import oogasalad.engine.model.Game;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;

/**
 * @author Haris Adnan
 * Class that saves a game to a log and creates a JSON File that is saved.
 *
 */
public class SaveGameEngine extends Engine{

  public SaveGameEngine(Game game) {
    super(game, null, null);
  }

  @Override
  public Board onCellSelect(int x, int y) throws OutOfBoardException {
    return null;
  }
  public void saveGame(){
    Board board = getGame().getBoard();
  }


}
