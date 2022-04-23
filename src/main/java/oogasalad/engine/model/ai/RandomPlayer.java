package oogasalad.engine.model.ai;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.engine.Oracle;
import oogasalad.engine.model.player.Player;

/**
 * Implements random player which selects a move at
 * random from all available choices
 *
 * @author Jake Heller
 */
public class RandomPlayer extends Player {

  private Oracle oracle;

  public RandomPlayer(Oracle oracle,
      BiConsumer<Player, Choice> executeMove) {
    super(executeMove);
  }

  @Override
  public void setGameBoard(Board board) {
    super.setGameBoard(board);
    Stream<Choice> choices = getOracle().getValidChoices(getGameBoard());
    List<Choice> choicesList = choices.toList();
    Random rand = new Random();
    int index = rand.nextInt(choicesList.size());
    executeMove(this, choicesList.get(index));
  }

  @Override
  public void onCellSelect(int i, int j) {

  }

  protected Oracle getOracle() {
    return oracle;
  }
}
