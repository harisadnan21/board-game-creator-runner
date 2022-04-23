package oogasalad.engine.model.ai;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.engine.Oracle;
import oogasalad.engine.model.player.AbstractPlayer;
import oogasalad.engine.model.player.Player;

/**
 * Implements random player which selects a move at
 * random from all available choices
 *
 * @author Jake Heller
 */
public class RandomPlayer extends AbstractPlayer {

  public RandomPlayer(Oracle oracle,
      BiConsumer<Player, Choice> executeMove) {
    super(oracle, executeMove);
  }

  @Override
  public void chooseMove(Board board) {
    setGameBoard(board);
    Stream<Choice> choices = getOracle().getValidChoices(getGameBoard());
    List<Choice> choicesList = choices.toList();
    Random rand = new Random();
    int index = rand.nextInt(choicesList.size());
    executeMove(this, choicesList.get(index));
  }

  @Override
  public void onCellSelect(int i, int j) {

  }
}
