package oogasalad.engine.model.engine;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.terminal_conditions.WinCondition;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.move.Move;

import oogasalad.engine.model.player.Player;
import org.jooq.lambda.function.Consumer0;


public abstract class Engine {

  public Engine(Game game, Collection<Move> moves,
      Collection<WinCondition> winConditions, Consumer<Board> update,
      Consumer<Set<Position>> setValidMarks) {
  }

  public Engine(Game game){
  }

  public abstract void gameLoop();

  public abstract void onCellSelect(int x, int y)
      throws OutOfBoardException;

  public abstract Board getGameStateBoard();
}
