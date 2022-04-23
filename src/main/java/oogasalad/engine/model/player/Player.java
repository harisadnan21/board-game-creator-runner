package oogasalad.engine.model.player;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.engine.Oracle;

/**
 * Player class
 */
public interface Player {

  /**
   * Engine uses this function to ping player and indicate it
   * should send a move choice
   */
  void chooseMove(Board activeBoard);

  /**
   * Defines what should happen if the user clicks a cell during
   * this player's turn
   * @param i
   * @param j
   */
  void onCellSelect(int i, int j);

  /**
   * Returns current score for this player
   * score should update after every game
   * @return
   */
  int getScore();

  /**
   * updates score for this player
   * @param change
   */
  void updateScore(int change);

  /**
   * Sets dependencies for player
   * TODO: Don't like this method but do not currently see another way to
   * @param oracle
   * @param executeMove
   * @param setValidMarks
   */
  void addDependencies(Oracle oracle, BiConsumer<Player, Choice> executeMove,
      Consumer<Set<Position>> setValidMarks);
}