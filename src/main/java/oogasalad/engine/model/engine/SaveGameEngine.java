package oogasalad.engine.model.engine;

import java.util.Set;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.move.Rule;

/**
 * @author Haris Adnan
 * Class that saves a game to a log and creates a JSON File that is saved.
 *
 */
public class SaveGameEngine extends Engine{

  public SaveGameEngine(Game game) {
    super(game, null, null, null, null, null);
  }

  @Override
  public void onCellSelect(int x, int y) throws OutOfBoardException {
  }

  @Override
  public Set<Rule> getValidMoves(Board board, int i, int j) {
    return null;
  }

  @Override
  public Board getGameStateBoard() {
    return null;
  }

  public void saveGame(){
    Board board = getGame().getBoard();
  }


}
